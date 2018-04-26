package com.aws.codestar.projecttemplates.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.DataAccessException;

import com.aws.codestar.projecttemplates.util.TreeNode;
import com.aws.codestar.projecttemplates.model.Category;
import com.aws.codestar.projecttemplates.model.Poll;
import com.aws.codestar.projecttemplates.model.PollOption;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.model.Vote;
import com.aws.codestar.projecttemplates.model.Comment;
import com.aws.codestar.projecttemplates.service.CategoryService;
import com.aws.codestar.projecttemplates.service.PollService;
import com.aws.codestar.projecttemplates.service.UserService;
import com.aws.codestar.projecttemplates.service.VoteService;
import com.aws.codestar.projecttemplates.service.CommentService;
import com.aws.codestar.projecttemplates.service.PollOptionService;

@Controller
public class PollController {
	
	@Autowired
	private PollService pollService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService catService;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PollOptionService pollOptionService;
	
	@Autowired
	private VoteService voteService;

	private int myPollId;
	
	@RequestMapping("/poll/{id}")
	public String getPoll(@PathVariable int id, ModelMap pollModel) {
		Poll poll = pollService.getPoll(id);
		pollModel.addAttribute("poll", poll);
		
		// Get the poll category
		Category cat = catService.getCategory(poll.getCategoryId());
		pollModel.addAttribute("category", cat);

		List<Comment> comments = commentService.getCommentsByPollId(id);
		comments = addUsername(comments, userService);
		List<TreeNode> commentsTree = makeCommentsTree(comments);
		myPollId = id;
		String htmlTree = getFormattedTrees(commentsTree);
		
		pollModel.addAttribute("listComments", comments);
		pollModel.addAttribute("treeComments", commentsTree);
		pollModel.addAttribute("htmlTree", htmlTree);
		pollModel.addAttribute("myPollId", myPollId);

		List<PollOption> pollOptions = pollOptionService.getPollOptionsByPoll(id);
		pollModel.addAttribute("listPollOptions", pollOptions);
		
		//Check to see if user has already submitted a vote for the poll
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.getUser(userName);
		
		List<Vote> userVotes = voteService.getVotesByPollIdAndUserId(id, user.getUserid());
		if (userVotes.size() > 0) {
			PollOption userPollOption = pollOptionService.getPollOption(userVotes.get(0).getPollOptionId());
			pollModel.addAttribute("userPollOption", userPollOption);
		} else {
			pollModel.addAttribute("newVote", new Vote());
		}

		return "poll";
	}
	
	@RequestMapping(value = "addPoll")
	public String addPage(ModelMap pollModel) {
		
		// Get list of all categories
		List<Category> categories = catService.getCategories();
		pollModel.addAttribute("categoryList", categories);
		
		pollModel.addAttribute("newPoll", new Poll());
		
		return "createpoll";
	}
	
	@RequestMapping(value = "/add/poll", method = RequestMethod.POST)
	public String addPoll(@ModelAttribute("newPoll") Poll submittedPoll, BindingResult result, ModelMap pollModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.getUser(userName);
		
		submittedPoll.setUserId(user.getUserid());
		pollService.addPoll(submittedPoll);
		
		pollModel.addAttribute("msg", "Poll added successfully");
		pollModel.addAttribute("poll", submittedPoll);
		return "poll";
	}
	
	@RequestMapping(value = "/addPollOptions", method = RequestMethod.POST)
	public String addPollOptions(ModelMap pollOptionsModel) {
		pollOptionsModel.addAttribute("newOption", new PollOption());
		
		return "addPollOption";
	}
	
	@RequestMapping(value = "/vote", method = RequestMethod.POST)
	public String addVote(@ModelAttribute("newVote") Vote submittedVote, BindingResult result, ModelMap pollModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.getUser(userName);
		
		submittedVote.setUserId(user.getUserid());
		voteService.addVote(submittedVote);
		
		pollModel.addAttribute("msg", "Vote submitted successfully");
		pollModel.addAttribute("pollId", submittedVote.getPollId());
		
		return "redirect:/poll/{pollId}";
	}

	@RequestMapping(value = "/createComment")
	public String createComment(@RequestParam(value = "pollId", required = true) int pollId,
	        @RequestParam(value = "parentId", required = true) int parentId,
	        ModelMap commentModel
	        ) 
	{

		commentModel.addAttribute("pollId", pollId);
		commentModel.addAttribute("parentId", parentId);

	    return "createComment";
	}

	@RequestMapping(value = "/commentCreateResult", method = RequestMethod.POST)
	public String addComment(@RequestParam(value = "pollId", required = true) int pollId,
	        @RequestParam(value = "parentId", required = true) int parentId,
	        @RequestParam(value = "content", required = true) String content,
	        ModelMap commentModel) {

	    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
	    int user_id = userService.getUserId(userName);

	    Comment comment = new Comment();
	    comment.setPollId(pollId);
	    comment.setContent(content);
	    comment.setUserId(user_id);
	    comment.setUsername(userName);
	    comment.setParentId(parentId);

	    try {
	        commentService.addComment(comment);
	        commentModel.addAttribute("addCommentSuccess", "Comment added successfully");
	    } catch (DuplicateKeyException e) {
	        String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
	        commentModel.addAttribute("failReason", reason);
	    } catch (DataAccessException e) {
	        String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
	        commentModel.addAttribute("failReason", reason);
	    }

	    commentModel.addAttribute("origPollId", pollId);

	    return "redirect:/poll/{origPollId}";
	}

	// Helper functions

	public List<Comment> addUsername(List<Comment> comments, UserService userService){
		for (Comment comment:comments){
			String username = userService.getUser(comment.getUserId()).getUsername();
			comment.setUsername(username);
		}
		return comments;
	}

	// This function is very, very inefficient. However, it works.
	// If performsnce becomes an issue, this can be optimized.
	public List<TreeNode> makeCommentsTree(List<Comment> comments){

	    List<TreeNode> commentsTree = new ArrayList<TreeNode>();
	    List<TreeNode> tempTree = new ArrayList<TreeNode>();

	    for (Comment comment:comments){
	    	TreeNode<Comment> node = new TreeNode<Comment>(comment);
	        tempTree.add(node);
	    }

	    for (TreeNode<Comment> node1:tempTree){
	        for (TreeNode<Comment> node2:tempTree){
	            if (node1.getData().getId() == node2.getData().getParentId()){
	                node1.addChild(node2);
	            }
	        }
	    }

	    for (TreeNode<Comment> node:tempTree){
	        if (node.getData().getParentId() == 0){
	            commentsTree.add(node);
	        }
	    }
	    
	    return commentsTree;
	}
	
	private String generateHTML(List<TreeNode> nodeArray){
		String html = "<ul>";
		for (int i = 0; i <nodeArray.size(); i++) {
			TreeNode<Comment> node = nodeArray.get(i);
			html += "<li class='jstree-open'>" + node.getData().getContent();
			if (node.getChildren().size() > 0) {
				html += generateHTML(node.getChildren());
			}
			html += "</li>";
		}
		
		html += "</ul>";

		return html;
	}

	private String formatContent(Comment comment){
		String html = "<table border="+1+">";
		html += "<tr><th>User</th><th>Content</th><th>Time Posted</th><th>Add Comment</th></tr>";
		html += "<tr><th><a href='/profile/"+comment.getUsername()+"'>"+comment.getUsername()+"</a></th><td>"+comment.getContent()+"</td><td>"+comment.getCreated()+"</td><td><a href='/createComment?parentId="+comment.getId()+"&pollId="+myPollId+"'>Add Comment</a></td></tr>";
		html += "</table>";
		return html;
	}

	// This should ideally be done in javascript
//	private String generateHTML_1(TreeNode<Comment> node){
//		String html = "<ul>";
//		
//		html += "<li>" + node.getData().getContent();
//		if (node.getChildren().size() == 0){
//			html += "</li>";
//			return html;
//		}
//		for (TreeNode<Comment> child:node.getChildren()) {
//				html += "<ul>";
//		        html += generateHTML(child);
//		        html += "</ul>";
//		}
//
//		return html;
//	}
	
	private String getFormattedTrees(List<TreeNode> commentsTree){
		//List<String> lis = new ArrayList<String>();
		String html = generateHTML(commentsTree);
		//lis.add(html);
		
		return html;
	}

//	private List<String> getFormattedTrees_1(List<TreeNode> commentsTree){
//		List<String> lis = new ArrayList<String>();
//		for (TreeNode<Comment> node:commentsTree){
//			String html = generateHTML(node);
//			lis.add(html);
//		}
//		return lis;
//	}

}

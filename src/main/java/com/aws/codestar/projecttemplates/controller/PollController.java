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
	
	@RequestMapping("/poll/{id}")
	public String getPoll(@PathVariable int id, ModelMap pollModel) {
		Poll poll = pollService.getPoll(id);
		pollModel.addAttribute("poll", poll);
		
		// Get the poll category
		Category cat = catService.getCategory(poll.getCategoryId());
		pollModel.addAttribute("category", cat);

		List<Comment> comments = commentService.getCommentsByPollId(id);

		List<TreeNode<Comment>> commentsTree = makeCommentsTree(comments);
		
		pollModel.addAttribute("listComments", comments);
		pollModel.addAttribute("treeComments", commentsTree);
		
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

	public List<TreeNode<Comment>> makeCommentsTree(List<Comment> comments){

	    List<TreeNode<Comment>> commentsTree = new ArrayList<TreeNode<Comment>>();
	    List<TreeNode<Comment>> tempTree = new ArrayList<TreeNode<Comment>>();

	    for (Comment comment:comments){
	    	TreeNode<Comment> node = new TreeNode<Comment>(comment);
	        tempTree.add(node);
	    }

	    for (TreeNode<Comment> node:tempTree){
	        if (node.getData().getParentId() == 0){
	            commentsTree.add(node);
	        }
	        else {
	            commentsTree = addChildToParent(commentsTree, node);
	        }
	    }
	    return commentsTree;
	}

	private List<TreeNode<Comment>> addChildToParent(List<TreeNode<Comment>> commentsTree, TreeNode<Comment> node){
	    // Since a comment can only have one parent, we can break once we find the parent
	    for (TreeNode<Comment> parentNode:commentsTree){
	        if (parentNode.getData().getId() == node.getData().getParentId()){
	            parentNode.addChild(node);
	            break;
	        }
	    }
	    return commentsTree;
	}

}

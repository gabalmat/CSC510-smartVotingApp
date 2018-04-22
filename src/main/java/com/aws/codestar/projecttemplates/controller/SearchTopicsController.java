package com.aws.codestar.projecttemplates.controller;

import com.aws.codestar.projecttemplates.model.SimplePoll;
import com.aws.codestar.projecttemplates.model.SearchCriteria;

import java.util.List;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchTopicsController{

    private final String siteName;

    protected JdbcTemplate jdbcTemplateObject;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
          this.dataSource = dataSource;
          this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @ModelAttribute("searchResults")
    public SearchCriteria setUpSearch() {
        return new SearchCriteria();
    }

    public SearchTopicsController(final String siteName) {
        this.siteName = siteName;
    }

    @GetMapping("/searchTopics")
    public ModelAndView searchTopics() {
        ModelAndView mav = new ModelAndView("searchTopics");
        
        mav.addObject("siteName", this.siteName);
        mav.addObject("searchTopics", "Search Topics");

        return mav;
    }

    @PostMapping("/searchResults")
    public ModelAndView searchResults(@ModelAttribute("searchResults") SearchCriteria criteria) {
        ModelAndView mav = new ModelAndView("searchResults");

        // String crit = criteria.getCriteria();
        String crit = "with";
        mav.addObject("criteria", crit);
        
        List<SimplePoll> results = queryDB(crit);
        mav.addObject("searchCount", results.size());
        mav.addObject("listPolls", results);

        return mav;
    }

    public List queryDB(String criteria){

        List<SimplePoll> polls = jdbcTemplateObject.query(
            "SELECT * FROM polls WHERE LOWER(title) LIKE ? OR LOWER(description) LIKE ?",
            new Object[]{"%"+criteria+"%", "%"+criteria+"%"},
            new RowMapper<SimplePoll>() {
                public SimplePoll mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SimplePoll poll = new SimplePoll();
                    poll.setTitle(rs.getString("title"));
                    poll.setID(rs.getInt("id"));
                    return poll;
                }
            });
        return polls;
    }

}


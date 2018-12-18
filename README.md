#NCSU CSC 510 (Software Engineering) Final Project
*SmartVoting* web application will allow its subscribers or voters to easily access debates on particular issues before voting, and then after voting, the application could allow the voter to cite which argument/priority in the debate tree swayed them most to make their selection. Some items to consider for SmartVoting include:
1.	The application shall accept voter subscriptions for specific issues. For example, if a company is considering making a change to a product, potential voters should be able to subscribe to this topic. Similarly, it could be an issue that the Government (local or Federal) may be considering as a potential to draft legislation.
2.	The application should be able to show subscribers the different topics that are being considered for voting.
3.	The application shall also allow to follow discussion trails and voters should be able to provide their ideas and display them into a tree-like structure.
4.	The application shall allow users to provide a vote on a particular topic.
5.	The application shall allow users interested in providing feedback on elements in a discussion that made them decide on a particular vote.


Getting Started
---------------

These directions assume you want to develop on your local computer, and not
from the Amazon EC2 instance itself. If you're on the Amazon EC2 instance, the
virtual environment is already set up for you, and you can start working on the
code.

To work on the sample code, you'll need to clone your project's repository to your
local computer. If you haven't, do that first. You can find instructions in the
AWS CodeStar user guide.

1. Install maven.  See https://maven.apache.org/install.html for details.

2. Install tomcat.  See https://tomcat.apache.org/tomcat-7.0-doc/setup.html for
   details.

3. Build the application.

        $ mvn -f pom.xml compile
        $ mvn -f pom.xml package

4. Copy the built application to the Tomcat webapp directory.  The actual
   location of that directory will vary depending on your platform and
   installation.

        $ cp target/ROOT.war <tomcat webapp directory>

4. Restart your tomcat server

5. Open http://127.0.0.1:8080/ in a web browser to view your application.

What Do I Do Next?
------------------

Once you have a virtual environment running, you can start making changes to
the sample Java web application. We suggest making a small change to
/src/main/webapp/WEB-INF/views/index.jsp first, so you can see how changes
pushed to your project's repository are automatically picked up and deployed
to the Amazon EC2 instance by AWS Elastic Beanstalk. (You can watch the progress
on your project dashboard.) Once you've seen how that works, start developing
your own code, and have fun!

To run your tests locally, go to the root directory of the sample code and run the
`mvn clean compile test` command, which AWS CodeBuild also runs through your `buildspec.yml` file.

To test your new code during the release process, modify the existing tests or add tests
to the tests directory. AWS CodeBuild will run the tests during the build stage of your
project pipeline. You can find the test results in the AWS CodeBuild console.

Learn more about Maven's [Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html).

Learn more about AWS CodeBuild and how it builds and tests your application here:
https://docs.aws.amazon.com/codebuild/latest/userguide/concepts.html

Learn more about AWS CodeStar by reading the user guide.  Ask questions or make
suggestions on our forum.

User Guide: http://docs.aws.amazon.com/codestar/latest/userguide/welcome.html

Forum: https://forums.aws.amazon.com/forum.jspa?forumID=248

What Should I Do Before Running My Project in Production?
------------------

AWS recommends you review the security best practices recommended by the framework
author of your selected sample application before running it in production. You
should also regularly review and apply any available patches or associated security
advisories for dependencies used within your application.

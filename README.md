# Kibbutz
Make poor decisions together!
## Description
Kibbutz is a social media platform that allows people to crowdsource their decisions, either to the public or to friends. Individuals make a post with a survey of potential decisions and set a time limit of when a decision needs to be made. Users then vote on which decision the original poster should make. Once the time limit passes, the poster can post photographic proof of their completion of the most-voted choice and will receive a “karma” score that is based on the number of votes that they received. This score increases or decreases depending on whether the community found the photographic proof satisfying. 
### Example User Story:
Jack is unsure of what sandwich to get for lunch. He posts a survey with three options and a time limit of 2 minutes. After two minutes, Jack selects a turkey sandwich, which was the most upvoted answer. He then posts an image of him eating the said turkey sandwich and improves his karma score. 
### Risks
Inherently, Kibbutz will motivate a certain group of people to perform dangerous or even illegal activity for internet karma. Kibbutz will need a comprehensive user policy that sets strict guidelines about what kind of decisions are acceptable to crowdsource.
### Potential Methods of Monetization
Like most sites, Kibbutz can make money through advertisement. Kibbutz could also run special “sponsor” days, where the extra karma is awarded to people who crowdsource decisions involving the sponsor’s products. Kibbutz can also sell the data of various decisions to various companies. For example, it may turn out that the community seems to value Skittles over Starburst in head-on decisions.

## Project Design

This project uses Spring MVC as the main framework that glues the project together. I use Bulma for all front-end stylings. For the backend, I  use JPA for object-relational mapping and Hibernate and MySQL as the persistence provider. 

### Use Cases
1.   User posts survey
2.   User votes on survey
3.   User posts photographic evidence of decision
4.   User adds another user to friend list
5.   User determines whether post is public or for friends

## Schedule
### Milestone 1 (completed)
1.	Set up databases and objects (User, Survey, etc.)
2.	User registration
3.   User login
4.	Allow users to post to the site (basic survey and time limit)
5.	Allow users to vote on others’ posts

### Milestone 2 (completed)
1.   Allow users to comment on other's posts
2.   Allow users to follow/friend other users
3.   Allow OP to post photographic proof 
4. Allow users to determine their satisfaction with proof
7. Create/improve current form validations


### Milestone 3 (completed)
1.	Stylize site using CSS and JavaScript
      *	Main page, user page, posts, feed, etc.
2.	Optimize karma algorithm
3.	Fix any remaining bugs
4. Allow users to suggest answers
5. Create more survey templates

### Additional Features to Add
1. Integration with Twitter followers
2. Search bar functionality
3. Allow users to see what they voted for

After decisions are made, photographic proof is required to receive a healhy dose of internet karma. 

## Project Justification
This project fuses different aspects of existing social media applications such as Reddit, Instagram, and Snapchat. Specifically, it solves the "OP please deliver" issue that many social media platforms suffer. Hopefully, it will be a fun way for users to stave off boredom and discover a new source of entertainment on the internet and among friends. This project is a good demonstration of my skills as it requires knowledge of the full stack. I will be working on both the back-end and front-end of the project, which will display my skills with object-orientation, Java, SQL, CSS, HTML, and JavaScript.

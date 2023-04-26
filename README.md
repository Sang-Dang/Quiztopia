# Assignment

## 0. Members
- Đặng Anh Sang SE170287
- Đỗ Thành Tú SE63390
- Nguyễn Phạm Quốc Thắng SE161786

## 1. Requirements
* **System name**: Online quiz website
* **Purpose**: With online learning becoming more and more of a ubiquity in 21st century life, we would like to provide teachers with a way to create fun, interactive quizzes for their students, and share those quizzes easily.
* **Target audience:** Teachers and Students

> ## System Requirements
> 1. As a user, I can
> - Login and Register a new account
> - Choose a quiz from a list of available quizzes
> - Open a quiz given a "Quiz ID" and "Password"
> - Answer multiple choice questions within a given time limit
> - View the scores and correct answers after completing the quiz
> - View the leaderboard and ranking of other quiz takers for a particular quiz
> 
> 2. As a teacher, I can
> - Do all the things a student can do
> - Create a quiz with multiple choice or written questions
> - Set a time limit for the quiz
> - Set a difficulty level for each question
> - Publish the quiz publically on the homepage of the website
> - Publish the quiz privately with access granted via "Quiz Code" and "Password"
> - View statistics on quizzes
> 

## 2. System GUI Design

![Wireframe](https://user-images.githubusercontent.com/59560341/233794376-46aef863-525d-49a0-9706-11caee0a141a.png)

**- Student screen:**
![quizlist student](https://user-images.githubusercontent.com/59560341/233799348-9a145e37-c3b7-4277-bd59-2ca9df390b92.jpg)
**+ Quiz list**

![join quiz ](https://user-images.githubusercontent.com/59560341/233799346-d7d7d2ef-5289-4bb9-9a39-a202a70ecb6d.jpg)
**+ Join a quiz**

![startquiz](https://user-images.githubusercontent.com/59560341/233799350-7b630462-58f1-4344-b13b-51fec0dbc922.jpg)
**+ Start quiz**

![result](https://user-images.githubusercontent.com/59560341/233799349-6129fa6a-ae49-4fe6-8d35-d16828a573da.jpg)
**+ Result**

**Teacher Screen:** 
![image](https://user-images.githubusercontent.com/59560341/233799670-6f0a8441-fcc9-4455-a958-121d0ea2b071.png)
**- Quiz List**

![image](https://user-images.githubusercontent.com/59560341/233799679-f5c76a4a-9131-44eb-b922-c22c0e0ada6a.png)
**+ Quiz manager**

![image](https://user-images.githubusercontent.com/59560341/233799750-6ccfa5f3-042d-4e29-b02f-cfc0474f73b6.png)
**+ Add/Edit Quiz**

![image](https://user-images.githubusercontent.com/59560341/233799775-ba5e8f3f-50d4-404f-9c10-0d9ead4b89d9.png)
**+ Quiz Statistics**

![image](https://user-images.githubusercontent.com/59560341/233799801-62316a2b-20d0-48c3-a586-b50205518620.png)
**+ View Quiz**

## 3. Database design
ER Diagram:
![image](https://user-images.githubusercontent.com/92671777/233877568-ef289d8a-1f29-4e5d-8ccc-eecaf1d60f2d.png)

## 4. System Design
The system was designed according to MVC2 standards, and we designed it in the order Model -> Controller -> View. This, of course, was all started by the creation of the database (which we created in Azure), and the formation of some basic DTO classes. Then we developed the necessary DAO classes for each DTO, each with separate implementations of basic CRUD methods (in addition to a list all method). Besides those methods, we also created helper methods for specific use-cases such as fetchQuizByUserId, or fetchPublicQuizzes. 

This is where we started to slightly deviate from traditional MVC2. Instead of putting all business logic in the Servlets, we decided to split the business logic up and put them in another package - the Service package. Functions in this package would contain full methods such as "login", "register", "createQuiz" with parameter validation and support for committing and rolling back Connections. This separation helped us debug code more easily, and it helped simplify the code in the servlets (which were harder to debug). 

While business login was dealt with using methods in the service package, we limited the servlets to the following responsibilities as much as we could: Fetch request parameters, Execute service package methods, handle errors and exceptions, and determine response/requestDispatcher. In accordance with MVC2, our project has two central servlets: ActionController and ViewController. As the name suggests, ActionController is used for executing specific actions, such as "login", "register", "addQuiz", "startQuiz"... and can be accessed via the path /action. ViewController (/home), on the other hand, is used to call Servlets that prepare data for JSPs, such as fetching a list and storing it in request attributes for the JSP to display. This way, we could limit the amount of Java code in JSPs to only JSTL and EL.

In the View part of MVC2, we used JSPs (stored in the WEB-INF folder) in combination with Custom Tag libraries, JSTL, EL, jQuery, and SCSS. We also used methods provided by the HTTPComponents Maven Dependency to assist in rewriting URLs. Additional tools include Online AI Chatbots which we used to generate SQL Insert statements, get feedback for design choices, and generate the more repetitive parts of our code, such as the implementation of CRUD methods in the DAO class (which are mostly similar for almost all DTOs).

## 5. Conclusion and discussion
Pros:
- Fairly intuitive
- Low coupling and high cohesion in classes
- Secure against common URL rewriting
- Uses Azure SQL Databases for easy scaling
Cons:
- Quite slow because of the number of connections to the database that need to be made per user
- Lacking some essential features: User details, Quiz timer...
- No responsive design nor accessibility features
- Inefficient code in some places, could be replaced with a framework.

What we've learnt throughout this project:
- It is time-consuming and extremely difficult to develop efficient, fast, and reliable web applications with many features, especially in a short time frame. We should pay more respect to most websites online, even if they are buggy and laggy at times. 
- It is immensely better to use pre-built frameworks instead of manually creating functionality.
- Do not code when sleep deprived. 
- Logging code should be added to most classes, alongside a local log file that logs all activity on the application.
- Using Github for version control is extremely beneficial, especially among teams.
- Thorough planning before development can help avoid many small bugs. 

Improvements that could be made:
- Utilization of Hibernate Validator and Hibernate ORM instead of creating DAO classes and Validation Classes.
- Usage of more SQL queries that involve the "JOIN" operator to account for foreign keys (in our project, we just made two connections to fetch data from both tables in the foreign key)
- Better code structure with documentation and error processing.
- Better HTML and CSS structure with further utilization of custom tags
- Implementation of a Connection Pool to manage large volumes of connections. 
- Security features such as Password encryption and hashing
- Better storage of login details (via a seperate database table instead of storing them in the session)
- More features such as update quiz, quiz timer...

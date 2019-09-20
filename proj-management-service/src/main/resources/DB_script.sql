create database projectmanager;

use projectmanager;

CREATE TABLE Parent_Task (
    Parent_ID int(11) NOT NULL AUTO_INCREMENT,
    Parent_Task varchar(500) NOT NULL,
CONSTRAINT parent_pk PRIMARY KEY (Parent_ID)
) ;

insert into Parent_Task(Parent_Task) values ('Parent Task 1');
insert into Parent_Task(Parent_Task) values ('Parent Task 2');
insert into Parent_Task(Parent_Task) values ('Parent Task 3');

CREATE TABLE Users (
    User_ID int(11) NOT NULL AUTO_INCREMENT,
    First_Name varchar(500) NOT NULL,
Last_Name varchar(500) NOT NULL,
Employee_ID int(11) NOT NULL,
Active TINYINT(1),
CONSTRAINT users_pk PRIMARY KEY (User_ID)
) ;

insert into Users(First_Name,Last_Name,Employee_ID,Active) values ('Lewis','Caroll','100100',1);
insert into Users(First_Name,Last_Name,Employee_ID,Active) values ('Tom','Sawyer','100101',1);
insert into Users(First_Name,Last_Name,Employee_ID,Active) values ('Cindy','Melka','100102',1);
insert into Users(First_Name,Last_Name,Employee_ID,Active) values ('Nick','Ross','100103',1);
insert into Users(First_Name,Last_Name,Employee_ID,Active) values ('Rick','Daniel','100104',1);

CREATE TABLE Project (
    Project_ID int(11) NOT NULL AUTO_INCREMENT,
    Project varchar(500) NOT NULL,
    start_date date NOT NULL,
end_date date NOT NULL,
Priority int(11) NOT NULL,
User_ID int(11) NOT NULL,
CONSTRAINT project_pk PRIMARY KEY (Project_ID),
FOREIGN KEY (User_ID) REFERENCES Users(User_ID)  
);


insert into Project(Project,start_date,end_date,Priority,User_ID) values ('Fees','2019-06-02','2019-12-24',7,2);
insert into Project(Project,start_date,end_date,Priority,User_ID) values ('XYZ','2019-04-10','2019-11-05',15,3);


 CREATE TABLE Task (
  Task_ID int(11) NOT NULL AUTO_INCREMENT,
  Task varchar(500) NOT NULL,
  Start_Date date NOT NULL,
  End_Date date NOT NULL,
  Priority int(11) NOT NULL,
  Status TINYINT(1),  
  Parent_ID int(11) NOT NULL,
  Project_ID int(11) NOT NULL,
  User_ID int(11) NOT NULL,
  CONSTRAINT task_pk PRIMARY KEY (Task_ID),
  FOREIGN KEY fk_task(Parent_ID) REFERENCES Parent_Task(Parent_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY fk_proj(Project_ID) REFERENCES Project(Project_ID) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY fk_user(User_ID) REFERENCES Users(User_ID) ON UPDATE CASCADE
) ;

ALTER TABLE Task CHANGE COLUMN Parent_ID int(11) NOT NULL;

insert into Task(Task,Start_Date,End_Date,Priority,Status,Parent_ID,Project_ID,User_ID) values ('Task 1','2019-06-05','2019-12-24',10,1,1,1,1);

insert into Task(Task,Start_Date,End_Date,Priority,Status,Parent_ID,Project_ID,User_ID) values ('Task 2','2019-06-10','2019-07-05',17,1,2,1,4);
insert into Task(Task,Start_Date,End_Date,Priority,Status,Parent_ID,Project_ID,User_ID) values ('Task 3','2019-06-08','2019-06-22',3,1,2,2,3);
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/9/14 14:46:55                           */
/*==============================================================*/


drop table if exists t_article;

drop table if exists t_category;

drop table if exists t_chapter;

drop table if exists t_count;

drop table if exists t_manager;

drop table if exists t_mascotMusic;

drop table if exists t_person;

drop table if exists t_picture;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_article                                             */
/*==============================================================*/
create table t_article
(
   id                   varchar(40) not null,
   title                varchar(40),
   createDate           timestamp,
   picture              varchar(200),
   author               varchar(40),
   discribe             varchar(4000),
   category             int(4),
   primary key (id)
);

/*==============================================================*/
/* Table: t_category                                            */
/*==============================================================*/
create table t_category
(
   id                   varchar(32) not null,
   name                 varchar(40),
   createDate           timestamp,
   type                 varchar(0),
   userId               varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: t_chapter                                             */
/*==============================================================*/
create table t_chapter
(
   id                   varchar(40) not null,
   name                 varchar(40),
   createDate           timestamp,
   filteSize            double(10,1),
   mascotid             varchar(40),
   audio_path           varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: t_count                                               */
/*==============================================================*/
create table t_count
(
   id                   varchar(40) not null,
   name                 varchar(40),
   createDate           timestamp,
   lessonId             varchar(40),
   count                int(8),
   userid               varchar(32),
   updateTime           timestamp,
   primary key (id)
);

/*==============================================================*/
/* Table: t_manager                                             */
/*==============================================================*/
create table t_manager
(
   id                   varchar(32) not null,
   name                 varchar(40),
   password             varchar(40),
   phone                varchar(40),
   createDate           timestamp,
   solt                 varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: t_mascotMusic                                         */
/*==============================================================*/
create table t_mascotMusic
(
   id                   varchar(32) not null,
   createDate           timestamp,
   name                 varchar(40),
   imgUrl               varchar(200),
   author               varchar(40),
   beam                 varchar(40),
   grade                int(5),
   discribe             text,
   primary key (id)
);

/*==============================================================*/
/* Table: t_person                                              */
/*==============================================================*/
create table t_person
(
   id                   varchar(32) not null,
   name                 varchar(40),
   pictore              varchar(255),
   status               varchar(2),
   primary key (id)
);

/*==============================================================*/
/* Table: t_picture                                             */
/*==============================================================*/
create table t_picture
(
   id                   varchar(32) not null,
   name                 varchar(40),
   imgurl               varchar(255),
   createDate           timestamp,
   status               varchar(1),
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   varchar(32) not null,
   phone                varchar(13),
   name                 varchar(10),
   nickname             varchar(20),
   sex                  varchar(3),
   createDate           timestamp,
   img                  varchar(50),
   signature            varchar(1000),
   password             varchar(10),
   salt                 varchar(255),
   status               varchar(2),
   location             varchar(40),
   primary key (id)
);

alter table t_article add constraint FK_Reference_6 foreign key (author)
      references t_person (id) on delete restrict on update restrict;

alter table t_category add constraint FK_Reference_5 foreign key (userId)
      references t_user (id) on delete restrict on update restrict;

alter table t_chapter add constraint FK_Reference_1 foreign key (mascotid)
      references t_mascotMusic (id) on delete restrict on update restrict;

alter table t_count add constraint FK_Reference_8 foreign key (userid)
      references t_user (id) on delete restrict on update restrict;

alter table t_count add constraint FK_Reference_9 foreign key (lessonId)
      references t_category (id) on delete restrict on update restrict;

alter table t_mascotMusic add constraint FK_Reference_7 foreign key (author)
      references t_person (id) on delete restrict on update restrict;


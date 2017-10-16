create table Account
(
	id bigint auto_increment primary key,
	accountId varchar(32) not null,
	addressLine1 varchar(128) null,
	addressLine2 varchar(128) null,
	createdBy varchar(255) null,
	createdOn datetime null,
	emailAddress varchar(128) not null,
	firstName varchar(255) null,
	lastName varchar(255) null,
	notes varchar(255) null,
	password varchar(255) null,
	phoneNumber varchar(20) null,
	roleId bigint null,
	status int null,
	updatedBy varchar(255) null,
	updatedOn datetime null,
	organization_id bigint null,
	constraint UK_1mj5yjysk58rqsexrj1hfpv3x
		unique (accountId),
	constraint UK_gi2p6kiyjyn8dw7nf2y8amvv4
		unique (emailAddress)
);

create index FKqc9uuldfqmt5qhf6f99f3b169
	on Account (organization_id)
;


create table AccountPrivilege
(
	id bigint not null,
	accountId bigint auto_increment,
	privilegeId bigint not null,
	primary key (accountId, privilegeId),
	constraint FKbxhe27xjhrqmy6ln3urk82nn8
		foreign key (accountId) references Account (id)
)
;

create index FK8w8b04wm1m7jo79glgxkf5pjo
	on AccountPrivilege (privilegeId)
;

create table Device
(
	id bigint auto_increment
		primary key,
	createdBy varchar(32) null,
	createdOn datetime null,
	deviceId varchar(32) not null,
	updatedBy varchar(32) null,
	updatedOn datetime null,
	accountId bigint null,
	constraint UK_ktkbd0xm3q2nddw1xxtdaxjy7
		unique (deviceId),
	constraint FK486r3d85wfbyu517mysp9gybj
		foreign key (accountId) references Account (id)
)
;

create index FK486r3d85wfbyu517mysp9gybj
	on Device (accountId)
;

create table Organization
(
	id bigint auto_increment
		primary key,
	addressLine1 varchar(255) null,
	addressLine2 varchar(255) null,
	createdBy varchar(255) null,
	createdOn datetime null,
	emailAddress varchar(255) not null,
	name varchar(255) not null,
	phoneNumber varchar(255) null,
	updatedBy varchar(255) null,
	updatedOn datetime null,
	constraint UK_ggls4nk6mtk4iwjkyuflhi54j
		unique (emailAddress),
	constraint UK_griwilufaypfq6nxhupb1jfrv
		unique (name)
);

alter table Account
	add constraint FKqc9uuldfqmt5qhf6f99f3b169
		foreign key (organization_id) references Organization (id)
;


create table Privilege
(
	id bigint auto_increment
		primary key,
	createdBy varchar(255) null,
	createdOn datetime null,
	description varchar(255) null,
	name varchar(255) not null,
	updatedBy varchar(255) null,
	updatedOn datetime null
)
;

alter table AccountPrivilege
	add constraint FK8w8b04wm1m7jo79glgxkf5pjo
		foreign key (privilegeId) references Privilege (id)
;

create table Vehicle
(
	id bigint auto_increment
		primary key
)
;

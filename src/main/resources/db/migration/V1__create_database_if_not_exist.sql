create table account
(
	id bigint auto_increment primary key,
	account_id varchar(32) not null,
	display_name varchar(255) null,
	email varchar(255) null,
	manager_id bigint NOT NULL DEFAULT 0,
	notes varchar(512) null,
	password varchar(255) null,
	phone varchar(20) null,
	role_id bigint null,
	status int not null DEFAULT 1,
	address_line1 varchar(128) null,
	address_line2 varchar(128) null,
	created_at datetime null,
	updated_at datetime null,

	constraint UK_account_id	unique (account_id),
	constraint FK_manager_id_id foreign key (manager_id) references account (id),
	constraint FK_role_id_id foreign key (role_id) references role (id)
);

create index FK_index_manager_id on account (manager_id);
create index FK_index_role_id on account (role_id);

-- default password is '123456'
INSERT INTO account (account_id, display_name, password, email, phone, role_id, status, manager_id, notes,  address_line1, address_line2, created_at, updated_at) VALUES
('sysadmin', 'System Admin', '$2a$10$.aqE4x/isX22RERANs01Z.ksLh00ssQM99rci8H3mH/VvTCSlVV.W', 'admin@vd5.com',  null, null, 1, 0,  'notes', NULL , NULL , '2017-09-21 01:37:06', '2017-09-21 01:37:06');
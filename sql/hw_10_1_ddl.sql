--1.
create table tb_category(
    name varchar2(10)
    ,use_yn char(1) default 'Y'
);

--2.
create table tb_class_type(
    no varchar2(5)
    ,name varchar2(10)
    ,constraint pk_tb_class_type_no primary key(no)
);
--3.
alter table tb_category
add constraint pk_tb_category_name primary key(name);

--4.
alter table tb_class_type
modify no varchar2(10) not null;

--5.
alter table tb_category
modify name varchar2(20);
alter table tb_class_type
modify no varchar2(10);
alter table tb_class_type
modify name varchar2(20);

--6.
alter table tb_category
rename column name to category_name;
alter table tb_class_type
rename column no to class_type_no;
alter table tb_class_type rename column name to class_type_name;
--이거 두줄 한개의 alter table에서 못하나...?

--7.
alter table tb_category
rename constraint pk_tb_category_name to pk_category_name;
alter table tb_class_type
rename constraint pk_tb_class_type_no to pk_class_type_no;

--8.
INSERT INTO TB_CATEGORY VALUES ('공학','Y');
INSERT INTO TB_CATEGORY VALUES ('자연과학','Y');
INSERT INTO TB_CATEGORY VALUES ('의학','Y');
INSERT INTO TB_CATEGORY VALUES ('예체능','Y');
INSERT INTO TB_CATEGORY VALUES ('인문사회','Y');
COMMIT; 

--9.
create table tb_department(
    category varchar2(50)
    ,constraint fk_tb_department_category foreign key(category)
        references tb_category(category_name)
);

--10.
--grant create view to chun;
create view VW_학생일반정보
as
select student_no 학번   
        ,student_name 학생이름
        ,student_address 주소
from tb_student;

--11.
create view VW_지도면담
as
select s.student_name 학생이름
        ,d.department_name 학과이름
        ,p.professor_name 지도교수이름
from tb_student S, tb_department D, tb_professor P
where s.department_no = d.department_no
    and s.coach_professor_no = p.professor_no(+)
order by 2;

--12.
create view VW_학과별학생수
as
select department_name
        ,count(*) student_count
from tb_student S, tb_department D
where s.department_no = d.department_no
group by department_name
order by 1;

--13.
update vw_학생일반정보
set 학생이름 = '최민순'
where 학번 = 'A213046';
select * from vw_학생일반정보;

--14.
--with read only

--15.
select *
from (
        select class_no 과목번호
                ,class_name 과목이름
                ,count(*) "누적수강생수(명)"
        from (
                    select term_no
                            ,g.class_no
                            ,class_name
                    from tb_grade G join tb_class C
                        on g.class_no = c.class_no
                    where substr(g.term_no,1,4) between 2005 and 2009
                    --이거 2007이 아니라 2005로 해야 숫자가 맞음..
                )
        group by class_no, class_name
        order by count(*) desc
        )
where rownum between 1 and 3;

commit;














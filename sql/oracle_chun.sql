--===============================
--CHUN 계정
--===============================

--학과테이블
select * from tb_department;
--학생테이블
select* from TB_STUDENT;
--과목테이블
select* from tb_class;
--교수테이블
select* from tb_professor;
--교수과목테이블
select* from tb_class_professor;
--점수테이블
select* from tb_grade;

--[Basic SELECT]
--1. 학과 명, 계열
select department_name "학과 명"
        ,category 계열
from tb_department;
--2. 학과명 정원
select department_name||'의 정원은 '||capacity||'명 입니다.' "학과별 정원"
from tb_department;
--3. 국어국문학과 여학생 휴학중
select student_name
from tb_student
where department_no=001 and substr(student_ssn,8,1) in ('2','4') and absence_yn='Y';
--4.
select student_name
from tb_student
where student_no in ('A513079', 'A513090', 'A513091', 'A513110', 'A513119')
order by student_name desc;
--5 20<=capacity<=30 학과이름 계열
select department_name
        ,category
from tb_department
where capacity between 20 and 30;
--6 소속학과 없는 총장찾기
select professor_name
from tb_professor
where department_no is null;
--7 학과 null 학생
select *
from tb_student
where department_no is null;
--8. 선수과목 존재하는 과목 class_no
select class_no
from tb_class
where preattending_class_no is not null;
--9. 춘대학 category 중복없이distinct
select distinct category
from tb_department;
--10. 02학번 전주 재학생만 학번 이름 주민번호
select student_no
        ,student_name
        ,student_ssn
from tb_student
where extract(year from entrance_date) = 2002 and instr(student_address,'전주시')>0 and absence_yn='N'
order by student_name;
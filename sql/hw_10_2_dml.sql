--1. 
/*
select * 
from ALL_TAB_COLUMNS 
where TABLE_NAME = 'TB_CLASS_TYPE' ;
*/
insert all 
into tb_class_type values(01, '전공필수')
into tb_class_type values(02, '전공선택')
into tb_class_type values(03, '교양필수')
into tb_class_type values(04, '교양선택')
into tb_class_type values(05, '논문지도')
select * from dual;

--2.
create table TB_학생일반정보
as
select student_no 학번
        ,student_name 학생이름
        ,student_address 주소
from tb_student;

--3.
create table tb_국어국문학과
as
select student_no 학번
        ,student_name 학생이름
        ,substr(student_ssn,1,2)+decode(substr(student_ssn,8,1),'1', 1900, '2', 1900, 2000) 출생년도
        ,p.professor_name 교수이름
from tb_student S, tb_professor P
where s.coach_professor_no = p.professor_no(+);
commit;
--4.
update tb_department
set capacity = round(capacity*1.1);

--5.
update tb_student
set student_address = '서울시 종로구 숭인동 181-21'
where student_no = 'A413042';

--6.
update tb_student
set student_ssn = substr(student_ssn,1,6);

--7. department_no = 053, term_no = 200501, class_name = 피부생리학
--select문으로 학생 특정->update 뒤에 select문의 where절 cv
update tb_grade
set point = 3.5
where student_no = (select student_no
                            from tb_student
                            where student_name = '김명훈'
                                and department_no = (select department_no
                                                                from tb_department
                                                                where department_name ='의학과'))
    and term_no = 200501;

--8.
--tb_student에서 absence_yn = 'Y'인 휴학생 서브쿼리를 null로 set or delete
delete from tb_grade
where student_no in (select student_no
                            from tb_student
                            where absence_yn = 'Y');





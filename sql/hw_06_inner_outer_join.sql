--@실습문제 : INNER JOIN & OUTER JOIN
--1. 학번, 학생명, 학과명을 출력
select student_no 학번
        ,student_name 학생명
        ,department_name 학과명
from tb_student S, tb_department D
where s.department_no = d.department_no;

--2. 학번, 학생명, 담당교수명을 출력하세요.
--담당교수가 없는 학생은 '없음'으로 표시
select student_no 학번
        ,student_name 학생명
        ,nvl(professor_name,'없음') 담당교수명
from tb_student S, tb_professor P
where s.coach_professor_no = p.professor_no(+);

--3. 학과별 교수명과 인원수를 모두 표시하세요.
--인원수는 학생 얘기하는 거겠지 아마..
--근데 전혀 모르겠다! 못풀었음.
select d.department_name 학과
        ,p.professor_name 교수명
        ,s.student_name 학생명
from tb_department D, tb_professor P, tb_student S
where d.department_no = p.department_no
    and d.department_no = s.department_no
order by d.department_name
;
-- 4. 이름이 [~람]인 학생의 평균학점을 구해서 학생명과 평균학점(반올림해서 소수점둘째자리까지)과 같이 출력.
-- (동명이인일 경우에 대비해서 student_name만으로 group by 할 수 없다.)
select s.student_name as 학생명 
        ,round(avg(point),2) as 평균학점
from tb_student S, tb_grade G
where s.student_no = g.student_no
    and s.student_name like '%람'
group by s.student_no, s.student_name
;
select * from tb_grade;
--5. 학생별 다음정보를 구하라. 
/*
--------------------------------------------
학생명  학기     과목명    학점
--------------------------------------------
감현제	200401	전기생리학 	4.5
            .
            .
--------------------------------------------

*/
select s.student_no 학번
        ,s.student_name 학생명
        ,g.term_no 학기
        ,c.class_name 과목명
        ,g.point 학점
from tb_student S, tb_grade G, tb_class C
where s.student_no = g.student_no
    and g.class_no =c.class_no
;
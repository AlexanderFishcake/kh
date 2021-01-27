select * from TB_DEPARTMENT;
select * from TB_STUDENT;
select * from TB_CLASS;
select * from TB_CLASS_PROFESSOR;
select * from TB_PROFESSOR;
select * from  TB_GRADE;

--1. 영영문(002) 학번 이름 입학년도 빠른순
select student_no 학번
        ,student_name 이름
        ,to_char(entrance_date,'yyyy-mm-dd') 입학년도
from tb_student
where department_no = 002
order by entrance_date;
--2. 
select professor_name
        ,professor_ssn
from tb_professor
where length(professor_name)!=3;
--3. 교수이름 나이 남자 나이 오름차순
select professor_name 교수이름
        ,extract(year from sysdate)-(1900+substr(professor_ssn,1,2))+1 나이
from tb_professor
where substr(professor_ssn,8,1)=1
order by 2;
--4. 
select substr(professor_name,2) 이름
from tb_professor;
--5. 재수생?(19살=현역) ssn vs entrance
select student_no
        ,student_name
from tb_student
where extract(year from entrance_date)-(1900+substr(student_ssn,1,2))>19
order by 1 desc;
--6 2020년 크리스마스 몇요일?
select to_char(to_date('2020/12/25'),'day')
from dual;
--7. yy는 현재 년도를 기준으로 추측함(20), rr은 현재년도 기준 앞뒤로 50으로 추측함.
select to_char(TO_DATE('99/10/11','YY/MM/DD'), 'yyyy/mm/dd') "2099"
        ,to_char(TO_DATE('49/10/11','YY/MM/DD'), 'yyyy/mm/dd') "2049"
        ,to_char(TO_DATE('99/10/11','RR/MM/DD'), 'yyyy/mm/dd') "1999"
        ,to_char(TO_DATE('49/10/11','RR/MM/DD'), 'yyyy/mm/dd') "2049"
from dual;
--8. 
select student_no
        ,student_name
from tb_student
where substr(student_no,1,1)!='A';
--9. A517178
select round(avg(point),1) 평점
from tb_grade
where student_no='A517178';
--10.
select department_no
        ,count(*)
from tb_student
group by department_no
order by 1;
--11. 지도교수 없음=coach null
select count(*)
from tb_student
where coach_professor_no is null;
--12.
select substr(term_no,1,4) 년도
        ,round(avg(point),1) "년도 별 평점"
from tb_grade
where student_no='A112113'
group by substr(term_no,1,4)
order by 1;
--13. 학과group 휴학생/ 학과코드 휴학생수 n-y
select department_no 학과코드명
        ,sum(decode(absence_yn,'Y','1','N','0')) "휴학생 수"
from tb_student
group by department_no
order by 1;
--14. 동명이인. 동일이름/동명인 수
select student_name
        ,count(*)
from tb_student
group by student_name
having count(*)>=2
order by 1;
--15. A112113 년도/학기별 평점+ 년도누적평점, 총평점
select
nvl(substr(term_no,1,4), '') 년도
        ,nvl(substr(term_no,5,2), '') 학기
--        substr(term_no,1,4) 년도
--        ,substr(term_no,5,2) 학기
--        decode(grouping(substr(term_no,1,4)),0,nvl(substr(term_no,1,4),'a'),'b') 년도
--        ,decode(grouping(substr(term_no,5,2)),0,nvl(substr(term_no,5,2),'c'),'d ') 학기
        ,round(avg(point),1) 평점
from tb_grade
where student_no='A112113'
group by rollup(substr(term_no,1,4), substr(term_no,5,2))
order by 1,2;
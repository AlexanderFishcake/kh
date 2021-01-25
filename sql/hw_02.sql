--0
select *
from employee;
--1
select emp_name 직원명
        ,email 이메일
        ,length(email) "이메일 길이"
from employee;
--2
select emp_name 직원명
        ,substr(email,0,instr(email,'@', -1)-1) "이메일 아이디"
from employee;
--3
select emp_name 직원명
        ,'19' || substr(emp_no,1,2) 년생
        ,nvl(bonus,0) 보너스
from employee
where substr(emp_no,1,1) in '6';
--4
select count(phone) || '명' 인원
from employee
where substr(phone,1,3) not in '010';
--5
select emp_name 직원명
        ,extract(year from hire_date) || '년' || extract(month from hire_date) ||'월' 입사년월
from employee;
--6 사원번호 사원명 주민번호 성별 현재나이
select emp_id 사원번호
        ,emp_name 사원명
        ,substr(emp_no,1,8)||'******' 주민번호
        ,decode(substr(emp_no,8,1), '1', '남', '3', '남', '여') 성별
        ,extract(year from sysdate)-(
            case
                when substr(emp_no,8,1) in ('1','2') then 19||substr(emp_no,1,2)
                when substr(emp_no,8,1) in ('3','4') then 20||substr(emp_no,1,2)
            end
        ) 나이
from employee;
--7 직원명 직급코드 연봉(원)
select emp_name 직원명
        ,job_code 직급코드
        ,to_char(salary*(1+nvl(bonus,0))*12,'L9,999,999,999') "연봉(원)"
from employee;
--8 사번 사원명 부서코드 입사일
select emp_id 사번
        ,emp_name 사원명
        ,dept_code 부서코드
        ,hire_date 입사일
from employee
where dept_code in ('D5', 'D9') and extract(year from hire_date)=2004;
--9 직원명 입사일 근무일수
select emp_name 직원명
        ,hire_date 입사일
        ,trunc((sysdate-hire_date)) 근무일수
from employee
where quit_yn='N';
--10 직원명, 부서코드, 생년월일, 만나이
select emp_name 직원명
        ,dept_code 부서코드
        ,case
            when substr(emp_no,8,1) in ('1','2') then 19||substr(emp_no,1,2)||'년'||substr(emp_no,3,2)||'월'||substr(emp_no,5,2)||'일'
            when substr(emp_no,8,1) in ('3','4') then 20||substr(emp_no,1,2)||'년'||substr(emp_no,3,2)||'월'||substr(emp_no,5,2)||'일'
        end 생년월일
        ,trunc((sysdate-(case
            when substr(emp_no,8,1) in ('1','2') then to_date(19||substr(emp_no,1,6),'yyyymmdd')
            when substr(emp_no,8,1) in ('3','4') then to_date(20||substr(emp_no,1,6),'yyyymmdd')
        end))/365) 만나이
from employee;
--11
select nvl(sum(decode(extract(year from hire_date),1998,1)),0) "1998년"
        ,nvl(sum(decode(extract(year from hire_date),1999,1)),0) "1999년"
        ,nvl(sum(decode(extract(year from hire_date),2001,1)),0) "2001년"
        ,nvl(sum(decode(extract(year from hire_date),2002,1)),0) "2002년"
        ,nvl(sum(decode(extract(year from hire_date),2003,1)),0) "2003년"
        ,nvl(sum(decode(extract(year from hire_date),2004,1)),0) "2004년"
        ,sum(decode(quit_yn,'N',1)) 전체직원수
from employee;
--12 서코드가 D5이면 총무부, D6이면 기획부, D9이면 영업부로 처리하시오.(case 사용)
--   단, 부서코드가 D5, D6, D9 인 직원의 정보만 조회하고, 부서코드 기준으로 오름차순 정렬함.
select case dept_code
        when 'D5' then '총무부'
        when 'D6' then '기획부'
        when 'D9' then '영업부'
        end 부서
from employee
where dept_code in ('D5', 'D6', 'D9')
order by dept_code asc;
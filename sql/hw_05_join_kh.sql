--1. 2020년 12월 25일이 무슨 요일인지 조회하시오.
select to_char(to_date('2020/12/25'),'yyyy-mm-dd day')
from dual;

--2. 주민번호가 70년대 생이면서 성별이 여자이고, 성이 전씨인 직원들의 
--사원명, 주민번호, 부서명, 직급명을 조회하시오.
select e.emp_name 사원명
        ,e.emp_no 주민번호
        ,d.dept_title 부서명
        ,j.job_name 직급명
from employee E, department D, job J
where e.dept_code = d.dept_id
    and e.job_code = j.job_code
    and substr(emp_no,1,1)='7'
    and substr(emp_no,8,1)='2'
    and substr(emp_name,1,1)='전';

--3. 가장 나이가 적은 직원의 사번, 사원명, 나이, 부서명, 직급명을 조회하시오.
--못 풀었다. 모르겠다. min(age) ? group지으면 나머지는 어떻게 select 하라고..
select e.emp_id 사번
        ,emp_name 사원명
        ,extract(year from sysdate)
        -(substr(emp_no,1,2)
        + case
                when substr(emp_no,8,1) in ('1','2') then 1900
                when substr(emp_no,8,1) in ('3','4') then 2000
            end) 나이
        ,d.dept_title 부서명
        ,j.job_name 직급명
from employee E
    join department D
        on E.dept_code = d.dept_id
    join job J
        on E.job_code = J.job_code
order by 3;

select e.emp_id 사번
        ,e.emp_name 사원명
        ,a.age 나이
        ,d.dept_title 부서명
        ,j.job_name 직급명        
from employee E
    join department D
        on E.dept_code = d.dept_id
    join job J
        on E.job_code = J.job_code
    join (select emp_id
                    ,extract(year from sysdate)
                    -(substr(emp_no,1,2)
                    + case
                        when substr(emp_no,8,1) in ('1','2') then 1900
                        when substr(emp_no,8,1) in ('3','4') then 2000
                        end) as age
            from employee) A
        on E.emp_id = A.emp_id
--where a.age = (select max(a.age) from A)
where a.age=14
;

--4. 이름에 '형'자가 들어가는 직원들의 사번, 사원명, 부서명을 조회하시오.
select emp_id 사번
        ,emp_name 사원명
        ,d.dept_title 부서명
from employee E, department D
where e.dept_code = d.dept_id;

--5. 해외영업팀에 근무하는 사원명, 직급명, 부서코드, 부서명을 조회하시오.
select e.emp_name 사원명
        ,j.job_name 직급명
        ,e.dept_code 부서코드
        ,d.dept_title 부서명
from employee E, department D, job J
where e.dept_code = d.dept_id and e.job_code = j.job_code
    and d.dept_title like '%해외영업%';
    
--6. 보너스포인트를 받는 직원들의 사원명, 보너스포인트, 부서명, 근무지역명을 조회하시오.
--e.bonus가 null이 아닌 직원
select e.emp_name 사원명
        ,e.bonus 보너스포인트
        ,d.dept_title 부서명
        ,l.local_name 근무지역명
from employee E, department D, location L
where E.dept_code = d.dept_id and d.location_id = L.local_code
    and e.bonus is not null;
    
--7. 부서코드가 D2인 직원들의 사원명, 직급명, 부서명, 근무지역명을 조회하시오.
select e.emp_name 사원명
        ,j.job_name 직급명
        ,d.dept_title 부서명
        ,l.local_name 근무지역명
from employee E, department D, job J, location L
where e.dept_code = d.dept_id and e.job_code = j.job_code and d.location_id = L.local_code
    and e.dept_code = 'D2';
--8. 급여등급테이블의 등급별 최대급여(MAX_SAL)보다 많이 받는 직원들의 사원명, 직급명, 급여, 연봉을 조회하시오.
--(사원테이블과 급여등급테이블을 SAL_LEVEL컬럼기준으로 동등 조인할 것)
select e.emp_name as 사원명
        ,j.job_name as 직급명
        ,to_char(e.salary,'fml9,999,999,999') as 급여
        ,to_char(e.salary*12,'fml9,999,999,999') as 연봉
from employee E, job J, sal_grade S
where e.job_code = J.job_code
    and e.sal_level = s.sal_level
    and e.salary>max_sal;

--9. 한국(KO)과 일본(JP)에 근무하는 직원들의 
--사원명, 부서명, 지역명, 국가명을 조회하시오.
select emp_name as 사원명
        ,d.dept_title as 부서명
        ,l.local_name as 지역명
        ,l.national_code as 국가명
from employee E, department D, location L
where e.dept_code = d.dept_id
    and d.location_id = l.local_code;
    
--10. 같은 부서에 근무하는 직원들의 사원명, 부서코드, 동료이름을 조회하시오.
--self join 사용
select e1.emp_name as 사원명
        ,e1.dept_code as 부서코드
        ,e2.emp_name as 동료이름
from employee E1, employee E2
where E1.dept_code = e2.dept_code
    and E1.emp_id != e2.emp_id
order by e1.emp_id;

--11. 보너스포인트가 없는 직원들 중에서 직급이 차장과 사원인 직원들의 사원명, 직급명, 급여를 조회하시오.
--bonus is null, dept_code 
select e.emp_name 사원명
        ,j.job_name 직급명
        ,to_char(salary,'fml9,999,999,999') 급여
from employee E, job J
where E.job_code = J.job_code
    and bonus is null
    and J.job_name in ('차장', '사원');
--12. 재직중인 직원과 퇴사한 직원의 수를 조회하시오.
select sum(decode(quit_yn, 'Y', 0, 'N', 1)) as "재직중인 직원 수"
        ,sum(decode(quit_yn, 'Y', 1, 'N', 0)) as "퇴사한 직원 수"
from employee;
--1. 기술지원부에 속한 사람들의 사람의 이름,부서코드,급여를 출력하시오
select emp_name 이름
        ,dept_code 부서코드
        ,salary 급여
from employee
where dept_code = (select dept_id
                            from department
                            where dept_title = '기술지원부');

--2. 기술지원부에 속한 사람들 중 가장 연봉이 높은 사람의 이름,부서코드,급여를 출력하시오
with dept_table
as
(
        select emp_name, dept_code, salary
        from employee
        where dept_code = (select dept_id
                                    from department
                                    where dept_title = '기술지원부')
)
select emp_name
        ,dept_code
        ,salary
from dept_table
where salary = (select max(salary)  from dept_table);

--3.매니저가 있는 사원중에 월급이 전체사원 평균을 넘고 사번,이름,매니저 이름, 월급을 구하시오. 
--1. JOIN을 이용하시오
--2. JOIN하지 않고, 스칼라상관쿼리(SELECT)를 이용하기

--3-1
select e1.emp_no 사번
        ,e1.emp_name 이름
        ,e2.emp_name 매니저이름
        ,e1.salary 월급
from (select *
        from employee
        where manager_id is not null
        ) E1 join employee E2 on e1.manager_id = e2.emp_id
where e1.salary> (select avg(salary) from employee);

--3-2
select emp_no 사번
        ,emp_name 이름
        ,(
            select emp_name
            from employee
            where emp_id = e.manager_id
        ) 매니저이름
        ,salary 월급
from (select *
        from employee
        where manager_id is not null
        ) E
where salary> (select avg(salary) from employee);

--4. 같은 직급의 평균급여보다 같거나 많은 급여를 받는 직원의 이름, 직급코드, 급여, 급여등급 조회
select emp_name
        ,job_code
        ,salary
        ,sal_level
from employee E
where salary > (select trunc(avg(salary))
                    from employee
                    where job_code = E.job_code);
--5. 부서별 평균 급여가 3000000 이상인 부서명, 평균 급여 조회
--단, 평균 급여는 소수점 버림, 부서명이 없는 경우 '인턴'처리
select (
            select dept_title
            from department
            where dept_id = e.dept_code
            ) 부서명
        ,trunc(avg(salary)) 평균급여
from employee E
group by dept_code
having trunc(avg(salary))>3000000;

--6. 직급의 연봉 평균보다 적게 받는 여자사원의
--사원명,직급명,부서명,연봉을 이름 오름차순으로 조회하시오
--연봉 계산 => (급여+(급여*보너스))*12    
select emp_name 사원명
        ,(select job_name
            from job J
            where J.job_code = e.job_code) 직급명
        ,(select dept_title
            from department D
            where d.dept_id = e.dept_code) 부서명
        ,salary*(1+nvl(bonus,0))*12 연봉
from employee E
where salary < ( select avg(salary)
                        from employee
                        where job_code = E.job_code)
    and substr(emp_no,8,1) in ('2','4');

--7. 다음 도서목록테이블을 생성하고, 공저인 도서만 출력하세요.
--book_title로 그룹핑했을 때 count>1인 책만.
select * from tbl_books;

select *
from tbl_books
where book_title in (select book_title
                            from tbl_books B
                            group by book_title
                            having count(*)>1);








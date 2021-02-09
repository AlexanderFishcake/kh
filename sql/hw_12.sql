--@실습문제 :
--emp_copy에서 사원을 삭제할 경우 emp_copy_del테이블로 데이터를 이전시키는 trigger를 생성하세요.
--quit_date에 현재날짜를 기록할 것.
create table emp_copy_del
as
select E.*
from emp_copy E
where 1=2;

create or replace trigger trig_emp_copy_del
    before
    delete on emp_copy
    for each row --ORA-04082: NEW or OLD references not allowed in table level triggers
begin
    dbms_output.put_line('사원 id : ' || :old.emp_id);
    dbms_output.put_line('사원 이름 : ' || :old.emp_name);
    
    insert into emp_copy_del(emp_id, emp_name, emp_no, email, phone, dept_code, job_code
                                    , sal_level, salary, bonus, manager_id, hire_date, quit_date, quit_yn)
    values(:old.emp_id, :old.emp_name, :old.emp_no, :old.email, :old.phone, :old.dept_code
             , :old.job_code, :old.sal_level, :old.salary, :old.bonus, :old.manager_id
             , :old.hire_date, sysdate, :old.quit_yn);
--에러나는 코드
--ORA-04091: table KH.EMP_COPY is mutating, trigger/function may not see it
--    insert into emp_copy_del
--    select *
--    from emp_copy
--    where emp_id = :old.emp_id;

end;
/

select * from emp_copy;
select * from emp_copy_del;
commit;
rollback;
delete from emp_copy
where emp_id = '204';
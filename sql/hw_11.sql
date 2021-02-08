create sequence myseq
maxvalue 100
cycle;

declare
    nextId number;
    randNum number;
    total number :=0;
begin
    for n in 1..100 loop
        nextid := MYSEQ.nextval;
        randNum := trunc(dbms_random.value(0,1000));
        total := total+randNum;
        dbms_output.put_line('total : '||total);
        
        insert into tb_number
        values(nextId,randNum,sysdate);

    end loop;
end;
/
select * from tb_number;
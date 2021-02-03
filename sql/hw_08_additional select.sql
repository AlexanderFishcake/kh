--1. 학생이름과 주소지를 표시하시오. 단, 출력 헤더는 "학생 이름", "주소지"로 하고,
--정렬은 이름으로 오름차순 표시하도록 한다.
select student_name "학생 이름"
        ,student_address "주소지"
from tb_student
order by 1;

--2. 휴학중인 학생들의 이름과 주민번호를 나이가 적은 순서로 화면에 출력하시오.
select student_name
        ,student_ssn
from tb_student
where absence_yn='Y'
order by student_ssn desc;

--3. 주소지가 강원도나 경기도인 학생들 중 1900 년대 학번을 가진 학생들의 이름과 학번,
--주소를 이름의 오름차순으로 화면에 출력하시오. 단, 출력헤더에는 "학생이름","학번",
--"거주지 주소" 가 출력되도록 핚다.
select student_name "학생이름"
        ,student_no "학번"
        ,student_address "거주지 주소"
from tb_student
where regexp_like(student_address,'경기도|강원도')
--where student_address like '%경기도%'
    and substr(student_no,1,1) !='A';

--4. 현재 법학과 교수 중 가장 나이가 맋은 사람부터 이름을 확인핛 수 있는 SQL 문장을
--작성하시오. (법학과의 '학과코드'는 학과 테이블(TB_DEPARTMENT)을 조회해서 찾아
--내도록 하자)
select professor_name
        ,professor_ssn
from tb_professor
where department_no = (select department_no
                                    from tb_department
                                    where department_name = '법학과')
order by professor_ssn;

--5. 2004 년 2 학기에 'C3118100' 과목을 수강핚 학생들의 학점을 조회하려고 핚다. 학점이
--높은 학생부터 표시하고, 학점이 같으면 학번이 낮은 학생부터 표시하는 구문을
--작성해보시오
select student_no
        ,to_char(point,'9.99') point
from tb_grade
where term_no = 200402
    and class_no = 'C3118100'
order by point desc, student_no asc;

--6. 학생 번호, 학생 이름, 학과 이름을 학생 이름으로 오름차순 정렬하여 출력하는 SQL
--문을 작성하시오.
select student_no
        ,student_name
        ,(select department_name
            from tb_department D
            where D.department_no = S.department_no
        ) department_name
from tb_student S
order by student_name asc;

--7. 춘 기술대학교의 과목 이름과 과목의 학과 이름을 출력하는 SQL 문장을 작성하시오.
select class_name
        ,( select department_name
            from tb_department D
            where d.department_no = c.department_no
        ) department_name
from tb_class C;

--8. 과목별 교수 이름을 찾으려고 핚다. 과목 이름과 교수 이름을 출력하는 SQL 문을
--작성하시오.
select (select class_name
            from tb_class C
            where c.class_no = CP.class_no
        ) class_name
        ,(select professor_name
            from tb_professor p
            where p.professor_no = cp.professor_no
        ) professor_name
from tb_class_professor CP;

--9. 8 번의 결과 중 ‘인문사회’ 계열에 속핚 과목의 교수 이름을 찾으려고 핚다. 이에
--해당하는 과목 이름과 교수 이름을 출력하는 SQL 문을 작성하시오.
select class_name
        ,professor_name
from tb_class_professor CP, tb_class C, tb_professor P
where c.class_no = CP.class_no
    and p.professor_no = cp.professor_no
    and c.department_no in (select department_no
                                        from tb_department
                                        where category = '인문사회');
--10. ‘음악학과’ 학생들의 평점을 구하려고 핚다. 음악학과 학생들의 "학번", "학생 이름",
--"젂체 평점"을 출력하는 SQL 문장을 작성하시오. (단, 평점은 소수점 1 자리까지맊
--반올림하여 표시핚다.)
select student_no 학번
        ,(select student_name
            from tb_student
            where student_no=g.student_no
        ) "학생 이름"
        ,round(avg(point),1) "전체 평점"
from tb_grade G
group by student_no
having student_no in (select student_no
                                from tb_student S
                                where department_no = (select department_no
                                                                    from tb_department
                                                                    where department_name='음악학과'));
--11. 학번이 A313047 인 학생이 학교에 나오고 있지 않다. 지도 교수에게 내용을 젂달하기
--위핚 학과 이름, 학생 이름과 지도 교수 이름이 필요하다. 이때 사용핛 SQL 문을
--작성하시오. 단, 출력헤더는 ‚학과이름‛, ‚학생이름‛, ‚지도교수이름‛으로
--출력되도록 핚다.
select department_name 학과이름
        ,student_name 학생이름
        ,professor_name 지도교수이름
from tb_student S
    join tb_professor P
        on S.coach_professor_no = P.professor_no
    join tb_department D
        on s.department_no = d.department_no    
where student_no = 'A313047';
                                    
--12. 2007 년도에 '인갂관계롞' 과목을 수강핚 학생을 찾아 학생이름과 수강학기름 표시하는
select (select student_name
            from tb_student
            where student_no = g.student_no) student_name
        ,term_no
from tb_grade G
where substr(term_no,1,4)=2007
    and class_no = (select class_no
                            from tb_class
                            where class_name = '인간관계론');

select * from tb_grade;
select * from tb_class;
                                    
--13. 예체능 계열 과목 중 과목 담당교수를 핚 명도 배정받지 못핚 과목을 찾아 그 과목
--이름과 학과 이름을 출력하는 SQL 문장을 작성하시오.
--d.category = '예체능'                              
select class_name
        ,(select department_name
        from tb_department
        where department_no = c.department_no
        ) department_name
from tb_class C
    left join tb_class_professor CP
        using(class_no)
where professor_no is null
    and department_no in (select department_no
                                    from tb_department
                                    where category = '예체능');
                                    
--14. 춘 기술대학교 서반아어학과 학생들의 지도교수를 게시하고자 한다. 학생이름과
--지도교수 이름을 찾고 만일 지도 교수가 없는 학생일 경우 "지도교수 미지정‛으로
--표시하도록 하는 SQL 문을 작성하시오. 단, 출력헤더는 ‚학생이름‛, ‚지도교수‛로
--표시하며 고학번 학생이 먼저 표시되도록 한다.
select s.student_name 학생이름
        ,nvl(p.professor_name,'지도교수 미지정') 지도교수
from tb_student S left join tb_professor P
    on s.coach_professor_no = p.professor_no
where s.department_no = (select department_no
                                    from tb_department
                                    where department_name = '서반아어학과');

--15. 휴학생이 아닌 학생 중 평점이 4.0 이상인 학생을 찾아 그 학생의 학번, 이름, 학과
--이름, 평점을 출력하는 SQL 문을 작성하시오.
select s.student_no
        ,s.student_name
        ,D.department_name
        ,round(avg(point),2)
from tb_student S
    join tb_grade G on s.student_no = g.student_no
    join tb_department D using(department_no)
group by s.student_no, s.absence_yn, s.student_name,d.department_name,s.student_name
having avg(point)>=4.0
    and s.absence_yn = 'N';

--16. 환경조경학과 전공과목들의 과목 별 평점을 파악할 수 있는 SQL 문을 작성하시오.
select g.class_no
        ,c.class_name
        ,round(avg(point),2)
from tb_grade G join tb_class C on g.class_no = c.class_no
group by g.class_no, c.class_name
having g.class_no in (select class_no
                            from tb_class
                            where department_no = (select department_no
                                                                from tb_department
                                                                where department_name = '환경조경학과')
                                                                and class_type like '전공%'
                                                                )
order by 1;

--17. 춘 기술대학교에 다니고 있는 최경희 학생과 같은 과 학생들의 이름과 주소를 출력하는
select student_name
        ,student_address
from tb_student
where department_no = (select department_no
                                    from tb_student
                                    where student_name = '최경희');

--18. 국어국문학과에서 총 평점이 가장 높은 학생의 이름과 학번을 표시하는 SQL 문
select *
from(
    select student_no
            ,(select student_name
            from tb_student
            where student_no = g.student_no) student_name
            ,round(avg(point),3)
    from tb_grade G
    group by student_no
    having student_no in (select student_no
                                    from tb_student
                                    where department_no = (select department_no
                                                                        from tb_department
                                                                        where department_name = '국어국문학과'))
    order by avg(point) desc
    )
where rownum=1;

--19. 춘 기술대학교의 "환경조경학과"가 속한 같은 계열 학과들의 학과 별 전공과목 평점을
--파악하기 위핚 적젃핚 SQL 문을 찾아내시오. 단, 출력헤더는 "계열 학과명",
--"전공평점"으로 표시되도록 하고, 평점은 소수점 핚 자리까지맊 반올림하여 표시되도록
select (select department_name
        from tb_department
        where department_no = c.department_no) "계열 학과명"
        ,round(avg(point),1) 전공평점
from tb_grade G join tb_class C on g.class_no = c.class_no
group by c.department_no
having c.department_no in (select department_no
                                    from tb_department
                                    where category = (select category
                                                            from tb_department
                                                            where department_name = '환경조경학과'))
order by 1;
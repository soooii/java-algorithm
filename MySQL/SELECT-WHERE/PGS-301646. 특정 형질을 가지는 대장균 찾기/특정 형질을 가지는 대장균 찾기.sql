-- 코드를 작성해주세요
select COUNT(*) 
from ECOLI_DATA as e
where e.GENOTYPE & 2 = 0 AND 
    (e.GENOTYPE & 1 > 0 OR e.GENOTYPE & 4 > 0)
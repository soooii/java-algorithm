-- 코드를 작성해주세요
select i.ITEM_ID, i.ITEM_NAME, i.RARITY
from ITEM_INFO i, ITEM_TREE t
where t.ITEM_ID = i.ITEM_ID
    AND NOT EXISTS (
      SELECT 1 
      FROM ITEM_TREE e
      WHERE t.ITEM_ID = e.PARENT_ITEM_ID
  )
order by i.ITEM_ID DESC
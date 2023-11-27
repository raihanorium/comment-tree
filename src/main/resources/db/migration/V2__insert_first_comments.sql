INSERT INTO comments(parent_id, comment)
values (NULL, 'Top order comment 1'),
       (NULL, 'Top order comment 2'),
       (1, 'Comment 1 child 1'),
       (1, 'Comment 1 child 2'),
       (2, 'Comment 2 child 1'),
       (3, 'Comment 1 child 1 child 1');
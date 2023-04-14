CREATE TABLE biller (id UUID,
                     code text,
                     short_name text,
                     full_name text,
                     enabled boolean,
                     status text,
                     PRIMARY KEY (id));


INSERT INTO biller(id, code, short_name, full_name, enabled, status)
     VALUES (uuid(), 'PLDT', 'PLDT', 'PHILIPPINE LONG DISTANCE ...', true, 'ACTIVE');

CREATE TABLE biller (id UUID,
                     code text,
                     short_name text,
                     full_name text,
                     enabled boolean,
                     status text,
                     PRIMARY KEY (id));


INSERT INTO biller(id, code, short_name, full_name, enabled, status)
     VALUES (uuid(), 'NAWASA', 'NAWASA', 'NAWASA WATER', true, 'ACTIVE');

INSERT INTO biller(id, code, short_name, full_name, enabled, status)
    VALUES (e6b42b29-2411-4fc7-800d-2ee553579b41, 'PLDT', 'PLDT', 'PHILIPPINE LONG DISTANCE ....', true, 'ACTIVE');
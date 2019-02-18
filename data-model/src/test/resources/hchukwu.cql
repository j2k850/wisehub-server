use platform;

//User_credentials
INSERT INTO user_credentials (email, password,user_id) VALUES
('hchukwu@wisehub.io', 'hchukwu1234',206141e5-0a7a-4a71-9b14-ef03318becbf);

INSERT INTO dashboard_user_credentials (email, password,user_id) VALUES
('hchukwu@wisehub.io', 'hchukwu1234',206141e5-0a7a-4a71-9b14-ef03318becbf);

INSERT INTO dashboard_user_roles (dashboard_user_id, dashboard_role_id,created_at,updated_at) VALUES
(206141e5-0a7a-4a71-9b14-ef03318becbf, a8ba9267-0bf3-4b86-bb70-f1dcff6af701, toTimestamp(now()), toTimestamp(now()));

//Users
INSERT INTO users (user_id, bvn, name, email, addresses, created_at, updated_at) VALUES
(206141e5-0a7a-4a71-9b14-ef03318becbf, '20012218200', {first_name: 'Hassana', last_name: 'Chukwu'},
'hchukwu@wisehub.io', {'main': { street: '1 Wisehub Way', city: 'Lagos', postal_code: '101241', region: 'Victoria Island', country: 'Nigeria', phones: {'16467521228'}}},
toTimestamp(now()), toTimestamp(now()));

//Accounts
INSERT INTO accounts (account_id, branch_id, user_id, account_number, account_name, account_type, account_status, created_at, updated_at) VALUES
(a0e5c257-430d-452c-944e-938e2748caa6, f0f60aa8-54a9-4840-b70c-fe562b68842b, 206141e5-0a7a-4a71-9b14-ef03318becbf, 2004605806, 'BARBARA OSAZE IYAYI', 'CHECKING','ACTIVE',toTimestamp(now()), toTimestamp(now()));

//User financial Institutions
INSERT INTO user_fin_insts (user_id, created_at, fin_inst_id) VALUES (206141e5-0a7a-4a71-9b14-ef03318becbf, toTimestamp(now()), a0f60aa8-74a9-4840-b70c-fe562b68842b);


INSERT INTO accounts_by_productstatus (account_id, created_at, product_id, product_status) VALUES
(a0e5c257-430d-452c-944e-938e2748caa6,toTimestamp(now()),b0f60aa8-24a9-4840-b70c-fe562b68842b,{'recommended': { status: 'recommended', created_at: toTimestamp(now())},
'matched': { status: 'matched', created_at: toTimestamp(now())}});

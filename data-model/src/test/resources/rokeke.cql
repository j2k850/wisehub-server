use platform;

//User_credentials
INSERT INTO user_credentials (email, password,user_id) VALUES
('rokeke@wisehub.io', 'rokeke1234',a990ff4b-5fb9-4765-ae0e-3c9c978aa796);


INSERT INTO dashboard_user_credentials (email, password,user_id) VALUES
('rokeke@wisehub.io', 'rokeke1234',a990ff4b-5fb9-4765-ae0e-3c9c978aa796);

INSERT INTO dashboard_user_roles (dashboard_user_id, dashboard_role_id,created_at,updated_at) VALUES
(a990ff4b-5fb9-4765-ae0e-3c9c978aa796, a8ba9267-0bf3-4b86-bb70-f1dcff6af701, toTimestamp(now()), toTimestamp(now()));

//Users
INSERT INTO users (user_id, bvn, name, email, addresses, created_at, updated_at) VALUES
(a990ff4b-5fb9-4765-ae0e-3c9c978aa796, '50012218500', {first_name: 'Rayowa', last_name: 'Okeke'},
'rokeke@wisehub.io', {'main': { street: '1 Wisehub Way', city: 'Lagos', postal_code: '101241', region: 'Victoria Island', country: 'Nigeria', phones: {'16467521228'}}},
toTimestamp(now()), toTimestamp(now()));

//Accounts
INSERT INTO accounts (account_id, branch_id, user_id, account_number, account_name, account_type, account_status, created_at, updated_at) VALUES
(6be072b8-b711-4b60-bc3c-7ab906e61477, f0f60aa8-54a9-4840-b70c-fe562b68842b, a990ff4b-5fb9-4765-ae0e-3c9c978aa796, 5004605806, 'BARBARA OSAZE IYAYI', 'CHECKING','ACTIVE',toTimestamp(now()), toTimestamp(now()));

//User financial Institutions
INSERT INTO user_fin_insts (user_id, created_at, fin_inst_id) VALUES (a990ff4b-5fb9-4765-ae0e-3c9c978aa796, toTimestamp(now()), a0f60aa8-74a9-4840-b70c-fe562b68842b);

INSERT INTO accounts_by_productstatus (account_id, created_at, product_id, product_status) VALUES
(6be072b8-b711-4b60-bc3c-7ab906e61477,toTimestamp(now()),b0f60aa8-24a9-4840-b70c-fe562b68842b,{'recommended': { status: 'recommended', created_at: toTimestamp(now())},
'matched': { status: 'matched', created_at: toTimestamp(now())}});

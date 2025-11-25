INSERT INTO roles (id, name, description) VALUES
                                              (1, 'ADMIN', 'Quản trị viên hệ thống, có full quyền'),
                                              (2, 'USER', 'Người dùng thông thường (Khách du lịch)');

INSERT INTO users (id, email, password, provider, status) VALUES
    (1, 'admin_huy@example.com', '$2y$10$spT5sG1xCyizYdxSF2/gBeUKKA4EsTM3zEL9aKD6GfZ.IGPk0tjkq', 'LOCAL', 'ACTIVE');

INSERT INTO users (id, email, password, provider, status) VALUES
    (2, 'admin_phuong@example.com', '$2y$10$spT5sG1xCyizYdxSF2/gBeUKKA4EsTM3zEL9aKD6GfZ.IGPk0tjkq', 'LOCAL', 'ACTIVE');

INSERT INTO users (id, email, password, provider, status) VALUES
    (3, 'admin_hoang@example.com', '$2y$10$spT5sG1xCyizYdxSF2/gBeUKKA4EsTM3zEL9aKD6GfZ.IGPk0tjkq', 'LOCAL', 'ACTIVE');

INSERT INTO users (id, email, password, provider, status) VALUES
    (4, 'user_thach@example.com', '$2y$10$spT5sG1xCyizYdxSF2/gBeUKKA4EsTM3zEL9aKD6GfZ.IGPk0tjkq', 'LOCAL', 'ACTIVE');

INSERT INTO users (id, email, password, provider, status) VALUES
    (5, 'user_loc@example.com', '$2y$10$spT5sG1xCyizYdxSF2/gBeUKKA4EsTM3zEL9aKD6GfZ.IGPk0tjkq', 'LOCAL', 'ACTIVE');

-- User bị khóa (Để test tính năng login status INACTIVE)
INSERT INTO users (id, email, password, provider, status) VALUES
    (6, 'user_blocked@example.com', '$2y$10$spT5sG1xCyizYdxSF2/gBeUKKA4EsTM3zEL9aKD6GfZ.IGPk0tjkq', 'LOCAL', 'INACTIVE');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1); -- Huy là Admin
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1); -- Phương là Admin
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1); -- Hoàng là Admin

INSERT INTO user_roles (user_id, role_id) VALUES (4, 2); -- Thạch là User
INSERT INTO user_roles (user_id, role_id) VALUES (5, 2); -- Lộc là User
INSERT INTO user_roles (user_id, role_id) VALUES (6, 2); -- User Blocked
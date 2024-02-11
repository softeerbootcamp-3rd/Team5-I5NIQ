-- User Entity
INSERT INTO user(created_at, updated_at, name, id, password, license, role) VALUES
    ('2024-01-01 12:00:00','2024-01-01 12:00:00','user1','userId1','password1','NOT_APPLICABLE','USER'),
    ('2024-01-03 16:00:00','2024-01-03 16:00:00','user2','userId2','password2','CLASS1','USER'),
    ('2024-01-06 15:00:00','2024-01-06 15:00:00','user3','userId3','password3','CLASS2','USER');

-- Notice Entity
INSERT INTO notice (title, content, image_url, image_name) VALUES
    ('공지사항 제목', '공지사항1 내용입니다.', 'http://example.com/image1.jpg', 'image1.jpg'),
    ('공지사항 제목2', '공지사항2 내용입니다.', 'http://example.com/image2.jpg', 'image2.jpg'),
    ('공지사항 제목3', '공지사항3 내용입니다.', 'http://example.com/image3.jpg', 'image3.jpg'),
    ('공지사항 제목4', '공지사항4 내용입니다.', 'http://example.com/image4.jpg', 'image4.jpg');


-- Program Entity
INSERT INTO program (name, category, level, cost, qualification, estimated_duration, maximum_occupancy) VALUES
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_1', 90000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_2', 180000, 'HMG 드라이빙 익스피리언스 Level 1 이상 수료 (19~23년)', '총 190분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'N_MASTERS', 800000, 'HMG 드라이빙 익스피리언스 N 어드밴스드 이상 수료 (22년~23년)', '총 460분', 2),
    ('DRIVING_EXPERIENCE', 'KIA', 'LEVEL_1', 90000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'LEVEL_1', 120000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_PLEASURE', 'TAXI', 'CIRCUIT_RACE_TAXI', 40000, '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
    ('DRIVING_PLEASURE', 'HMG', 'TEST_DRIVE', 100000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '약 150분', 4);


-- Comment Entity
INSERT INTO comment (user_id, program_id, content) VALUES
    ('userId1', 1, '댓글 내용입니다.1'),
    ('userId1', 2, '댓글 내용입니다.2'),
    ('userId2', 1, '댓글 내용입니다.3');

-- ProgramImage Entity
INSERT INTO program_image (program_id, url, name) VALUES
    (1, 'http://example.com/program1-1.jpg', 'program1-1.jpg'),
    (1, 'http://example.com/program1-2.jpg', 'program1-2.jpg'),
    (1, 'http://example.com/program1-3.jpg', 'program1-3.jpg'),
    (2, 'http://example.com/program2-1.jpg', 'program2-1.jpg'),
    (2, 'http://example.com/program2-2.jpg', 'program2-2.jpg'),
    (3, 'http://example.com/program3-1.jpg', 'program3-1.jpg'),
    (3, 'http://example.com/program3-2.jpg', 'program3-2.jpg'),
    (4, 'http://example.com/program4-1.jpg', 'program4-1.jpg'),
    (5, 'http://example.com/program5-1.jpg', 'program5-1.jpg'),
    (6, 'http://example.com/program6-1.jpg', 'program6-1.jpg'),
    (7, 'http://example.com/program7-1.jpg', 'program7-1.jpg');

-- Car Entity
INSERT INTO car (name, engine, maximum_power, maximum_torque) VALUES
    ('아반떼 N Line', '가솔린 1.6 터보', '최고 출력 204 PS / 6,000 RPM', '최대 토크 27 kgf•m / 1,500 ~ 4,500 RPM'),
    ('아반떼 N DCT', '가솔린 N전용 2.0 터보', '최고 출력 280 PS / 5,500 ~ 6,000 RPM', '최대 토크 40 kgf•m / 2,100 ~ 4,700 RPM'),
    ('아반떼 N DCT R-Tune', '가솔린 N전용 2.0 터보', '최고 출력 280 PS / 5,500 ~ 6,000 RPM', '최대 토크 40 kgf•m / 2,100 ~ 4,700 RPM'),
    ('EV6 GT-Line', '롱레인지 4WD', '최고 출력 239 kW / 325 PS', '최대 토크 605 Nm / 61.7 kgf•m'),
    ('G70 2.0T AWD', '가솔린 2.0 터보 AWD', '최고 출력 252 PS / 6,200 RPM (스포츠패키지)', '최대 토크 36 kgf•m / 1,400 ~ 4,000 RPM'),
    ('아이오닉6', '롱레인지 20인치 HTRAC', '최고 출력 239 kW / 325 PS', '최대 토크 605 Nm / 61.7 kgf•m'),
    ('EV6 GT', 'GT 4WD', '최고 출력 430 kW / 585 PS', '최대 토크 740 Nm / 75.5 kgf•m'),
    ('G70 슈팅 브레이크', '가솔린 2.0 터보 AWD (스포츠패키지)', '최고 출력 255 PS / 6,200 RPM', '최대 토크 36.0 kgf•m / 4,000 RPM');

-- CarImage Entity
INSERT INTO car_image (car_id, url, name) VALUES
    (1, 'http://example.com/car_image1.jpg', 'car_image1.jpg'),
    (2, 'http://example.com/car_image2.jpg', 'car_image2.jpg'),
    (3, 'http://example.com/car_image3.jpg', 'car_image3.jpg'),
    (4, 'http://example.com/car_image4.jpg', 'car_image4.jpg'),
    (5, 'http://example.com/car_image5.jpg', 'car_image5.jpg'),
    (6, 'http://example.com/car_image6.jpg', 'car_image6.jpg'),
    (7, 'http://example.com/car_image7.jpg', 'car_image7.jpg'),
    (8, 'http://example.com/car_image8.jpg', 'car_image8.jpg');

-- SelectedCar Entity
INSERT INTO selected_car (car_id, program_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (3, 6),
    (2, 7),
    (6, 7),
    (7, 7),
    (8, 7);

-- DrivingClass Entity
INSERT INTO driving_class (program_id, start_date_time, reservation_start_time, reservation_deadline) VALUES
    (1, '2023-12-01 10:00:00', '2024-11-01 10:00:00', '2024-11-25 18:00:00'),
    (2, '2024-12-10 10:00:00', '2024-11-10 10:00:00', '2024-11-05 18:00:00'),
    (1, '2024-02-01 10:00:00', '2024-01-01 10:00:00', '2024-01-25 18:00:00'),
    (3, '2024-03-05 10:00:00', '2024-02-05 10:00:00', '2024-03-01 18:00:00'),
    (4, '2024-03-10 10:00:00', '2024-02-10 10:00:00', '2024-03-05 18:00:00'),
    (1, '2024-04-15 10:00:00', '2024-03-15 10:00:00', '2024-04-05 18:00:00'),
    (4, '2024-04-20 10:00:00', '2024-03-20 10:00:00', '2024-04-15 18:00:00'),
    (5, '2024-03-10 10:00:00', '2024-02-10 10:00:00', '2024-03-05 18:00:00'),
    (7, '2024-03-05 10:00:00', '2024-02-05 10:00:00', '2024-03-01 18:00:00');

-- ClassCar Entity
INSERT INTO class_car (maximum_occupancy, driving_class_id, car_id) VALUES
    (6, 1, 1),
    (4, 2, 2),
    (5, 3, 1),
    (4, 4, 3),
    (6, 5, 4),
    (4, 8, 5),
    (1, 9, 2),
    (1, 9, 6),
    (2, 9, 7),
    (2, 9, 8);

-- Participation Entity
INSERT INTO participation (user_id, class_car_id, completion, participants, paid_at, status) VALUES
    ('userId1', 1, true, 2, '2024-11-01 10:01:30', 'DEFAULT'),
    ('userId1', 2, true, 2, '2024-11-10 10:01:30', 'DEFAULT'),
    ('userId2', 1, true, 1, '2024-01-01 10:02:00', 'DEFAULT'),
    ('userId1', 4, false, 2, '2024-02-05 10:01:30', 'DEFAULT'),
    ('userId1', 5, false, 2, '2024-02-10 10:01:30', 'DEFAULT'),
    ('userId2', 4, false, 1, '2024-02-05 10:03:30', 'DEFAULT'),
    ('userId2', 6, false, 1, '2024-02-10 10:02:00', 'DEFAULT');




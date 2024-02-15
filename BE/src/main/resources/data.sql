-- User Entity
INSERT INTO user (created_at, updated_at, name, id, password, license, role) VALUES
    ('2024-01-01 12:00:00','2024-01-01 12:00:00','user1','userId1','password1','NOT_APPLICABLE','USER'),
    ('2024-01-03 16:00:00','2024-01-03 16:00:00','user2','userId2','password2','CLASS1','USER'),
    ('2024-01-06 15:00:00','2024-01-06 15:00:00','user3','userId3','password3','CLASS2','USER'),
    ('2024-02-01 12:00:00','2024-02-01 12:00:00','user4','userId4','password4','NOT_APPLICABLE','USER'),
    ('2024-02-03 16:00:00','2024-02-03 16:00:00','user5','userId5','password5','CLASS1','USER'),
    ('2024-02-06 15:00:00','2024-02-06 15:00:00','user6','userId6','password6','CLASS2','USER'),
    ('2024-01-01 12:00:00','2024-01-01 12:00:00','user7','userId7','password7','NOT_APPLICABLE','USER'),
    ('2024-01-03 16:00:00','2024-01-03 16:00:00','user8','userId8','password8','CLASS1','USER'),
    ('2024-01-06 15:00:00','2024-01-06 15:00:00','user9','userId9','password9','CLASS2','USER'),
    ('2024-02-01 12:00:00','2024-02-01 12:00:00','user10','userId10','password10','NOT_APPLICABLE','USER');

-- Notice Entity
INSERT INTO notice (title, content, image_url, image_name) VALUES
    ('공지사항 제목1', '공지사항1 내용입니다.', 'http://example.com/image1.jpg', 'image1.jpg'),
    ('공지사항 제목2', '공지사항2 내용입니다.', 'http://example.com/image2.jpg', 'image2.jpg'),
    ('공지사항 제목3', '공지사항3 내용입니다.', 'http://example.com/image3.jpg', 'image3.jpg'),
    ('공지사항 제목4', '공지사항4 내용입니다.', 'http://example.com/image4.jpg', 'image4.jpg'),
    ('공지사항 제목5', '공지사항5 내용입니다.', 'http://example.com/image5.jpg', 'image5.jpg'),
    ('공지사항 제목6', '공지사항6 내용입니다.', 'http://example.com/image6.jpg', 'image6.jpg'),
    ('공지사항 제목7', '공지사항7 내용입니다.', 'http://example.com/image7.jpg', 'image7.jpg'),
    ('공지사항 제목8', '공지사항8 내용입니다.', 'http://example.com/image8.jpg', 'image8.jpg'),
    ('공지사항 제목9', '공지사항9 내용입니다.', 'http://example.com/image9.jpg', 'image9.jpg'),
    ('공지사항 제목10', '공지사항10 내용입니다.', 'http://example.com/image10.jpg', 'image10.jpg'),
    ('공지사항 제목11', '공지사항11 내용입니다.', 'http://example.com/image11.jpg', 'image11.jpg'),
    ('공지사항 제목12', '공지사항12 내용입니다.', 'http://example.com/image12.jpg', 'image12.jpg'),
    ('공지사항 제목13', '공지사항13 내용입니다.', 'http://example.com/image13.jpg', 'image13.jpg'),
    ('공지사항 제목14', '공지사항14 내용입니다.', 'http://example.com/image14.jpg', 'image14.jpg'),
    ('공지사항 제목15', '공지사항15 내용입니다.', 'http://example.com/image15.jpg', 'image15.jpg'),
    ('공지사항 제목16', '공지사항16 내용입니다.', 'http://example.com/image16.jpg', 'image16.jpg'),
    ('공지사항 제목17', '공지사항17 내용입니다.', 'http://example.com/image17.jpg', 'image17.jpg'),
    ('공지사항 제목18', '공지사항18 내용입니다.', 'http://example.com/image18.jpg', 'image18.jpg'),
    ('공지사항 제목19', '공지사항19 내용입니다.', 'http://example.com/image19.jpg', 'image19.jpg'),
    ('공지사항 제목20', '공지사항20 내용입니다.', 'http://example.com/image20.jpg', 'image20.jpg'),
    ('공지사항 제목21', '공지사항21 내용입니다.', 'http://example.com/image21.jpg', 'image21.jpg'),
    ('공지사항 제목22', '공지사항22 내용입니다.', 'http://example.com/image22.jpg', 'image22.jpg'),
    ('공지사항 제목23', '공지사항23 내용입니다.', 'http://example.com/image23.jpg', 'image23.jpg'),
    ('공지사항 제목24', '공지사항24 내용입니다.', 'http://example.com/image24.jpg', 'image24.jpg'),
    ('공지사항 제목25', '공지사항25 내용입니다.', 'http://example.com/image25.jpg', 'image25.jpg'),
    ('공지사항 제목26', '공지사항26 내용입니다.', 'http://example.com/image26.jpg', 'image26.jpg'),
    ('공지사항 제목27', '공지사항27 내용입니다.', 'http://example.com/image27.jpg', 'image27.jpg'),
    ('공지사항 제목28', '공지사항28 내용입니다.', 'http://example.com/image28.jpg', 'image28.jpg'),
    ('공지사항 제목29', '공지사항29 내용입니다.', 'http://example.com/image29.jpg', 'image29.jpg'),
    ('공지사항 제목30', '공지사항30 내용입니다.', 'http://example.com/image30.jpg', 'image30.jpg');

-- Program Entity
INSERT INTO program (name, category, level, cost, qualification, estimated_duration, maximum_occupancy) VALUES
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_1', 90000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_2', 180000, 'HMG 드라이빙 익스피리언스 Level 1 이상 수료 (19~23년)', '총 190분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_3', 300000, 'HMG 드라이빙 익스피리언스 Level 2 이상 수료 (19~23년)', '총 260분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'N_ADVANCED', 450000, 'HMG 드라이빙 익스피리언스 Level 3 이상 수료 (19~23년)', '총 250분', 3),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'N_MASTERS', 800000, 'HMG 드라이빙 익스피리언스 N 어드밴스드 이상 수료 (22년~23년)', '총 460분', 2),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'OFF_ROAD', 50000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 90분', 2),
    ('DRIVING_EXPERIENCE', 'KIA', 'LEVEL_1', 90000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'KIA', 'LEVEL_2', 90000, 'HMG 드라이빙 익스피리언스 Level 1 이상 수료(19~23년)', '총 190분', 4),
    ('DRIVING_EXPERIENCE', 'KIA', 'LEVEL_3', 90000, 'HMG 드라이빙 익스피리언스 Level 2 이상 수료(19~23년)', '총 260분', 4),
    ('DRIVING_EXPERIENCE', 'KIA', 'OFF_ROAD', 90000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 90분', 2),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'LEVEL_1', 120000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'LEVEL_2', 240000, 'HMG 드라이빙 익스피리언스 LEVEL 1 이상 수료 (19~23년)', '총 190분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'LEVEL_3', 340000, 'HMG 드라이빙 익스피리언스 LEVEL 2 이상 수료 (19~23년)', '총 260분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'DRIFT_LEVEL_1', 450000, 'HMG 드라이빙 익스피리언스 LEVEL 3 이상 수료 (19~23년)', '총 200분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'DRIFT_LEVEL_2', 550000, 'HMG 드라이빙 익스피리언스 Drift Level 1 테스트 수료자 (20년~23년)', '총 220분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'OFF_ROAD', 60000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 90분', 2),
    ('DRIVING_PLEASURE', 'TAXI', 'CIRCUIT_RACE_TAXI', 40000, '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
    ('DRIVING_PLEASURE', 'HMG', 'TEST_DRIVE', 100000, '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '약 150분', 4);

-- Course Entity
INSERT INTO course (name, detail, image_url, image_name) VALUES
    ('다목적 주행 코스', '슬라럼, 짐카나 등 다양한 모듈로 구성되어 있는 코스로 드라이빙의 기본기부터 자동차의 가속 성능까지 종합적으로 경험할 수 있습니다.', 'http://example.com/course1.jpg', 'course1.jpg'),
    ('제동 코스', '다양한 노면과 상황 속에서 브레이크의 성능을 최대로 이끌어 내는 코스로 위급 상황 시 안전하게 대처하는 테크닉을 익힐 수 있습니다.', 'http://example.com/course2.jpg', 'course2.jpg'),
    ('마른 노면 서킷 B코스', '3.4km, 16개의 코너로 구성된 서킷에서 드라이빙 스킬을 종합적으로 경험할 수 있는 코스입니다. 레벨에 따라 두 개의 코스로 나누어 경험할 수 있습니다.', 'http://example.com/course3.jpg', 'course3.jpg'),
    ('킥 플레이트 코스', '돌발 상황에 대처하는 능력을 키우는데 특화된 코스로 킥 플레이트의 인위적인 힘에 의해 불안정해진 차량을 안전하게 컨트롤하는 능력을 키울 수 있습니다.', 'http://example.com/course2.jpg', 'course2.jpg'),
    ('마른 노면 서킷 A코스', '3.4km, 16개의 코너로 구성된 서킷에서 드라이빙 스킬을 종합적으로 경험할 수 있는 코스입니다. 레벨에 따라 두 개의 코스로 나누어 경험할 수 있습니다.', 'http://example.com/course3.jpg', 'course3.jpg'),
    ('젖은 노면 코스', '11개 코너, 1.6km의 젖은 서킷을 달리는 코스로 젖은 노면에서도 안전하게 자동차를 컨트롤하는 방법을 배울 수 있습니다.', 'http://example.com/course1.jpg', 'course1.jpg'),
    ('고속주회로', '38.87도의 뱅크각의 4.6km 오벌 트랙에서 자동차의 한계 속도와 극한의 중력 가속도를 체험할 수 있습니다.', 'http://example.com/course2.jpg', 'course2.jpg'),
    ('마른 노면 서킷 Full코스', '3.4km, 16개의 코너로 구성된 서킷에서 드라이빙 스킬을 종합적으로 경험할 수 있는 코스입니다. 레벨에 따라 두 개의 코스로 나누어 경험할 수 있습니다.', 'http://example.com/course3.jpg', 'course3.jpg'),
    ('태안, 서산 일대 지방도로', '센터 외부로 나가 태안, 서산 일대 지방도로를 드라이빙하며 한국의 서해안 지역의 독특한 풍경과 함께 평화롭고 여유로운 드라이빙을 경험할 수 있습니다', 'http://example.com/course3.jpg', 'course3.jpg'),
    ('오프로드 코스', '보기만 해도 아찔한 구간를 통과하며 SUV의 내구성과 오프로드 성능을 체험하는 코스입니다. 언덕 경사, 모랫길, 계단 등 11가지 즐거운 장애물이 기다리고 있습니다.', 'http://example.com/course3.jpg', 'course3.jpg'),
    ('젖은 원선회 코스', '서로 다른 노면 위에서 눈으로만 보았던 드리프트를 실제로 경험해보는 코스입니다. 카운터 스티어링을 비롯한 고급 기술을 경험할 수 있습니다.', 'http://example.com/course3.jpg', 'course3.jpg');

-- Comment Entity
INSERT INTO comment (user_id, program_id, content) VALUES
    ('userId1', 1, '(1) 나 userId1인데 익스피리언스/현대/레벨1 너무 재밌다'),
    ('userId1', 2, '(2) 나 userId1인데 익스피리언스/현대/레벨2 너무 어렵다'),
    ('userId2', 1, '(3) 나 userId2인데 익스피리언스/현대/레벨1 너무 무섭다'),
    ('userId4', 3, '(4) 나 userId4인데 익스피리언스/현대/레벨3 너무 좋았다'),
    ('userId5', 4, '(5) 나 userId5인데 익스피리언스/현대/n_advanced 너무 쉬웠따'),
    ('userId6', 5, '(6) 나 userId6인데 익스피리언스/현대/n_masters 짱 재밌다');


-- ProgramCourse Entity
INSERT INTO program_course (program_id, course_id) VALUES
    (1, 1), (1, 2), (1, 3),
    (2, 1), (2, 4), (2, 5),
    (3, 1), (3, 6), (3, 8), (3, 7),
    (4, 2), (4, 8),
    (5, 6), (5, 7), (5, 8),
    (6, 10),
    (7, 1), (7, 2), (7, 3),
    (8, 1), (8, 7), (8, 4), (8, 5),
    (9, 1), (9, 6), (9, 8), (9, 7),
    (10, 10),
    (11, 1), (11, 2), (11, 3),
    (12, 1), (12, 4), (12, 5),
    (13, 1), (13, 6), (13, 8), (13, 7),
    (14, 11),
    (15, 6),
    (16, 10),
    (17, 8),
    (18, 1), (18, 3), (18, 9);

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
    ('아이오닉 5 N', 'EV AWD', '최고출력 478 kW / 650 PS', '최대토크 770 Nm / 78.5 kgf•m'),
    ('아이오닉6', '롱레인지 20인치 HTRAC', '최고 출력 239 kW / 325 PS', '최대 토크 605 Nm / 61.7 kgf•m'),
    ('EV6 GT', 'GT 4WD', '최고 출력 430 kW / 585 PS', '최대 토크 740 Nm / 75.5 kgf•m'),
    ('G70 슈팅 브레이크', '가솔린 2.0 터보 AWD (스포츠패키지)', '최고 출력 255 PS / 6,200 RPM', '최대 토크 36.0 kgf•m / 4,000 RPM'),
    ('투싼', '디젤 2.0 터보 HTRAC', '최고출력 184 PS / 4,000 RPM', '최대토크 42.5 kgf•m / 2,000 ~ 2,750 RPM'),
    ('팰리세이드', '디젤 2.2 터보 HTRAC', '최고 출력 202 PS / 3,800 RPM', '최대 토크 45 kgf•m / 1,750 ~ 2,750 RPM'),
    ('스포티지', '디젤 2.0 터보 4WD', '최고 출력 184 PS / 4,000 RPM', '최대 토크 42.5 kgf•m / 2,000 ~ 2,750 RPM'),
    ('모하비', '디젤 V6 3.0 터보 4WD', '최고출력 257 PS / 3,800 RPM', '최대토크 57.1 kgf•m / 1,500 ~ 3,000 RPM'),
    ('G70 3.3T AWD', '가솔린 V6 3.3 트윈터보 AWD (스포츠패키지)', '최고출력 373 PS / 6,000 RPM', '최대토크 52 kgf•m / 1,300 ~ 4,500 RPM'),
    ('G70 3.3T RWD', '가솔린 V6 3.3 트윈터보 AWD (스포츠패키지)', '최고출력 373 PS / 6,000 RPM', '최대토크 52 kgf•m / 1,300 ~ 4,500 RPM'),
    ('GV70 2.2D AWD', '디젤 2.2 터보 AWD', '최고출력 202 PS / 3,800 RPM', '최대토크 45 kgf•m / 1,750 ~ 2,750 RPM'),
    ('GV80 3.0D AWD', '디젤 직렬 6기통 3.0 터보 AWD', '최고출력 273 PS / 3,800 RPM', '최대토크 60 kgf•m / 1,500 ~ 3,000 RPM');


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
INSERT INTO selected_car (program_id, car_id) VALUES
    (1, 1),
    (2, 2),
    (3, 2), (3, 6),
    (4, 2),
    (5, 3),
    (6, 10), (6, 11),
    (7, 4),
    (8, 8),
    (9, 8),
    (10, 12), (10, 13),
    (11, 5),
    (12, 14), (12, 15),
    (13, 14), (13, 15),
    (14, 15),
    (15, 15),
    (16, 16), (16, 17),
    (17, 3),
    (18, 2), (18, 7), (18, 8), (18, 9);

-- DrivingClass Entity
INSERT INTO driving_class (program_id, start_date_time, reservation_start_time, reservation_deadline) VALUES
    (1, '2024-03-02 10:00:00', '2024-02-14 10:00:00', '2024-03-01 18:00:00'),
    (9, '2024-03-02 10:00:00', '2024-02-14 10:00:00', '2024-03-01 18:00:00'),
    (2, '2024-03-02 16:00:00', '2024-02-14 16:00:00', '2024-03-01 18:00:00'),
    (10, '2024-03-02 16:00:00', '2024-02-14 16:00:00', '2024-03-01 18:00:00'),
    (3, '2024-03-03 10:00:00', '2024-02-15 10:00:00', '2024-03-02 18:00:00'),
    (11, '2024-03-03 10:00:00', '2024-02-15 10:00:00', '2024-03-02 18:00:00'),
    (4, '2024-03-03 16:00:00', '2024-02-15 16:00:00', '2024-03-02 18:00:00'),
    (12, '2024-03-03 16:00:00', '2024-02-15 16:00:00', '2024-03-02 18:00:00'),

    (5, '2024-03-09 10:00:00', '2024-02-21 10:00:00', '2024-03-08 18:00:00'),
    (13, '2024-03-09 10:00:00', '2024-02-21 10:00:00', '2024-03-08 18:00:00'),
    (6, '2024-03-09 16:00:00', '2024-02-21 16:00:00', '2024-03-08 18:00:00'),
    (14, '2024-03-09 16:00:00', '2024-02-21 16:00:00', '2024-03-08 18:00:00'),
    (7, '2024-03-10 10:00:00', '2024-02-22 10:00:00', '2024-03-09 18:00:00'),
    (15, '2024-03-10 10:00:00', '2024-02-22 10:00:00', '2024-03-09 18:00:00'),
    (8, '2024-03-10 16:00:00', '2024-02-22 16:00:00', '2024-03-09 18:00:00'),
    (16, '2024-03-10 16:00:00', '2024-02-22 16:00:00', '2024-03-09 18:00:00'),

    (1, '2024-03-16 10:00:00', '2024-03-28 10:00:00', '2024-03-15 18:00:00'),
    (9, '2024-03-16 10:00:00', '2024-03-28 10:00:00', '2024-03-15 18:00:00'),
    (2, '2024-03-16 16:00:00', '2024-03-28 16:00:00', '2024-03-15 18:00:00'),
    (10, '2024-03-16 16:00:00', '2024-03-28 16:00:00', '2024-03-15 18:00:00'),
    (3, '2024-03-17 10:00:00', '2024-03-29 10:00:00', '2024-03-16 18:00:00'),
    (11, '2024-03-17 10:00:00', '2024-03-29 10:00:00', '2024-03-16 18:00:00'),
    (4, '2024-03-17 16:00:00', '2024-03-29 16:00:00', '2024-03-16 18:00:00'),
    (12, '2024-03-17 16:00:00', '2024-03-29 16:00:00', '2024-03-16 18:00:00');

-- ClassCar Entity
INSERT INTO class_car (driving_class_id, maximum_occupancy, car_id) VALUES
-- 3월 2일
    (1, 4, 1),
    (2, 4, 8),
    (3, 4, 2),
    (4, 1, 12), (4, 1, 13),
-- 3월 3일
    (5, 3, 2), (5, 3, 6), --최대 4인
    (6, 4, 5), -- 4자리중 3자리 예약
    (7, 3, 2), -- 3자리중 2자리 예약
    (8, 3, 14), (8, 3, 15);

-- Participation Entity
INSERT INTO participation (user_id, class_car_id, completion, participants, paid_at, status) VALUES
--3월 2일
    ('userId10', 1, false, 2, '2024-02-14 10:04:30', 'DEFAULT'),
    ('userId8', 1, false, 2, '2024-02-14 10:53:23', 'DEFAULT'),
    ('userId7', 2, false, 1, '2024-02-14 17:23:32', 'DEFAULT'),
    ('userId5', 2, false, 3, '2024-02-14 18:02:03', 'DEFAULT'),
    ('userId9', 3, false, 1, '2024-02-14 17:05:03', 'DEFAULT'),
    ('userId4', 3, false, 1, '2024-02-14 18:05:03', 'DEFAULT'),
    ('userId1', 3, false, 2, '2024-02-14 19:01:30', 'DEFAULT'),
    ('userId2', 4, false, 1, '2024-02-14 17:01:35', 'DEFAULT'),
    ('userId3', 5, false, 1, '2024-02-14 18:02:00', 'DEFAULT'),
--3월 3일
    ('userId10', 6, false, 3, '2024-02-15 10:04:30', 'DEFAULT'),
    ('userId8', 7, false, 1, '2024-02-15 10:53:23', 'DEFAULT'),
    ('userId7', 8, false, 2, '2024-02-15 17:23:32', 'DEFAULT'),
    ('userId5', 8, false, 1, '2024-02-15 18:02:03', 'DEFAULT'),
    ('userId9', 9, false, 1, '2024-02-15 17:05:03', 'DEFAULT'),
    ('userId4', 9, false, 1, '2024-02-15 18:05:03', 'DEFAULT'),
    ('userId1', 10, false, 2, '2024-02-15 19:01:30', 'DEFAULT'),
    ('userId2', 11, false, 2, '2024-02-15 17:01:35', 'DEFAULT');



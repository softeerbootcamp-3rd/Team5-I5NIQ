-- User Entity
INSERT INTO user (created_at, updated_at, name, id, password, license, role) VALUES
-- 1 ~ 5
    ('2024-01-01 12:00:00','2024-01-01 12:00:00','심채은','userId1','password1','NOT_APPLICABLE','USER'),
    ('2024-01-03 16:00:00','2024-01-03 16:00:00','이주원','userId2','password2','CLASS1','USER'),
    ('2024-01-06 15:00:00','2024-01-06 15:00:00','김태오','userId3','password3','CLASS2','USER'),
    ('2024-02-01 12:00:00','2024-02-01 12:00:00','정우진','userId4','password4','NOT_APPLICABLE','USER'),
    ('2024-02-03 16:00:00','2024-02-03 16:00:00','김서아','userId5','password5','CLASS1','USER'),
-- 6 ~ 10
    ('2024-02-06 15:00:00','2024-02-06 15:00:00','강예은','userId6','password6','CLASS2','USER'),
    ('2024-01-01 12:00:00','2024-01-01 12:00:00','우이서','userId7','password7','NOT_APPLICABLE','USER'),
    ('2024-01-03 16:00:00','2024-01-03 16:00:00','이예준','userId8','password8','CLASS1','USER'),
    ('2024-01-06 15:00:00','2024-01-06 15:00:00','김수민','userId9','password9','CLASS2','USER'),
    ('2024-02-01 12:00:00','2024-02-01 12:00:00','한선우','userId10','password10','NOT_APPLICABLE','USER');

-- Notice Entity
INSERT INTO notice (title, content, image_url, image_name, created_at, updated_at) VALUES
-- 1 ~ 10
    ('2022 현대 N 페스티벌 라운드 4 시즌 포인트 현황 (Time-trial)', '공지사항1 내용입니다.', 'http://example.com/image1.jpg', 'image1.jpg', '2022-07-29 00:00:00', '2022-07-29 00:00:00'),
    ('HMG 드라이빙 익스피리언스 센터 신규 오픈 안내', '공지사항2 내용입니다.', 'http://example.com/image2.jpg', 'image2.jpg', '2022-08-01 00:00:00', '2022-08-01 00:00:00'),
    ('HMG 드라이빙 익스피리언스 센터 런칭 이벤트 쿠폰 발급 방법 안내', '공지사항3 내용입니다.', 'http://example.com/image3.jpg', 'image3.jpg', '2022-08-01 01:00:00', '2022-08-01 01:00:00'),
    ('2022년 HMG 드라이빙 익스피리언스 티켓 오픈 안내', '공지사항4 내용입니다.', 'http://example.com/image4.jpg', 'image4.jpg', '2022-08-05 00:00:00', '2022-08-05 00:00:00'),
    ('NFT 수료증 발급 안내', '공지사항5 내용입니다.', 'http://example.com/image5.jpg', 'image5.jpg', '2022-08-12 00:00:00', '2022-08-12 00:00:00'),
    ('2022 현대 N 페스티벌 라운드 5 생중계 안내', '공지사항6 내용입니다.', 'http://example.com/image6.jpg', 'image6.jpg', '2022-09-19 00:00:00', '2022-09-19 00:00:00'),
    ('2022 현대 N 페스티벌 라운드 5 시즌 포인트 현황 (Sprint)', '공지사항8 내용입니다.', 'http://example.com/image7.jpg', 'image7.jpg', '2022-10-07 00:00:00', '2022-10-07 00:00:00'),
    ('2022 현대 N 페스티벌 라운드 5 시즌 포인트 현황 (Time-trial)', '공지사항8 내용입니다.', 'http://example.com/image8.jpg', 'image8.jpg', '2022-10-07 01:00:00', '2022-10-07 01:00:00'),
    ('2022 현대 N 페스티벌 라운드 6/7 생중계 안내', '공지사항9 내용입니다.', 'http://example.com/image9.jpg', 'image9.jpg', '2022-10-24 00:00:00', '2022-10-24 00:00:00'),
    ('2022 HMG 드라이빙 익스피리언스 운영 일정 공지', '2022년 운영 일정에 대해 안내드립니다.', 'http://example.com/image21.jpg', 'image21.jpg', '2022-11-29 00:00:00', '2022-11-29 00:00:00'),
-- 11 ~ 20
    ('2023 Hyundai N Festival 공식 연습 일정 안내', '2023 Hyundai N Festival 공식 테스트 신청이 오픈되었습니다.\n참가를 원하시는분들께서는 내용 확인 후\n신청바랍니다.\n 신청 링크 : https://naver.me/xGi9cyJb\n자세한 내용은 이미지 및 링크를 확인해주세요.', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/03/MicrosoftTeams-image-39.png', 'image22.jpg', '2023-03-27 00:00:00', '2023-03-27 00:00:00'),
    ('2023 Hyundai N Festival 선수 등록 안내', '2023 Hyundai N Festival 선수 등록이 시작되었습니다.\n엔트리 선택 후, 폼 작성을 하셔야 선수등록이 완료됩니다.\n\n 1. 엔트리 선택 : https://tuney.kr/8iA7rg\n 2. 선택 완료 후 폼 작성 : https://naver.me/5bd2lGoD\n\n* 엔트리 선택은 선착순입니다.', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/03/MicrosoftTeams-image-39.png', 'image23.jpg', '2023-03-27 01:00:00', '2023-03-27 01:00:00'),
    ('2023 Hyundai N Festival 규정집', '2023 Hyundai N Festival 규정집 입니다.', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/04/2023-%ED%8A%B9%EB%B3%84-%EA%B3%A0%EC%8B%9C-%EC%A0%9C1%ED%98%B8-JPG-1.jpg', 'image24.jpg', '2023-03-28 00:00:00', '2023-03-28 00:00:00'),
    ('2023 Hyundai N Festival 타이어 특별 규정', '2023 Hyundai N Festival 타이어 특별 규정입니다.', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/04/2023-%ED%8A%B9%EB%B3%84-%EA%B3%A0%EC%8B%9C-%EC%A0%9C1%ED%98%B8-JPG-1.jpg', 'image25.jpg', '2023-04-03 00:00:00', '2023-04-03 00:00:00'),
    ('2023 현대 N 페스티벌 Round1 참가접수 안내', '1라운드 참가접수\n2023 Hyundai N Festival 참가접수가 시작됩니다.\nHMG홈페이지를 통해 접수가 진행되오니,\n참가접수 방법 안내를 꼭 확인하세요!', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/04/2023-SNS-%EB%A3%A8%ED%8B%B4-%EA%B2%8C%EC%8B%9C%EB%AC%BC%EB%8C%80%EC%A7%80-1-%EC%82%AC%EB%B3%B8-30%EB%8C%80%EC%A7%80-1-%EC%82%AC%EB%B3%B8-29.jpg', 'image27.jpg', '2023-04-10 00:00:00', '2023-04-10 00:00:00'),
    ('2023 현대 N 페스티벌 N타임트라이얼 Round1 참가접수 안내', 'N타임트라이얼 Round1 참가접수\n2023 Hyundai N Festival 참가접수가 시작됩니다.\nHMG홈페이지를 통해 접수가 진행되오니,\n참가접수 방법 안내를 꼭 확인하세요!', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/05/Rd1-02-02.jpg', 'image28.jpg', '2023-05-08 00:00:00', '2023-05-08 00:00:00'),
    ('2023 현대 N 페스티벌 Round2 참가접수 안내', '2라운드 참가접수\n2023 Hyundai N Festival 참가접수가 시작됩니다.\nHMG홈페이지를 통해 접수가 진행되오니,\n참가접수 방법 안내를 꼭 확인하세요!', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/06/%EC%B0%B8%EA%B0%80%EC%A0%91%EC%88%98-02.jpg', 'image27.jpg', '2023-06-05 00:00:00', '2023-06-05 00:00:00'),
    ('2023 현대 N 페스티벌 N타임트라이얼 Round2 참가접수 안내', 'N타임트라이얼 Round2 참가접수\n2023 Hyundai N Festival 참가접수가 시작됩니다.\nHMG홈페이지를 통해 접수가 진행되오니,\n참가접수 방법 안내를 꼭 확인하세요!', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/06/355068983743148164478661351023485836531390n.jpg', 'image28.jpg', '2023-06-26 00:00:00', '2023-06-26 00:00:00'),
    ('2023 현대 N 페스티벌 Round3 참가접수 안내', '3라운드 참가접수\n2023 Hyundai N Festival 참가접수가 시작됩니다.\nHMG홈페이지를 통해 접수가 진행되오니,\n참가접수 방법 안내를 꼭 확인하세요!', 'https://drivingexperience.hyundai.co.kr/kr/image/2023/07/%EC%B0%B8%EA%B0%80%EC%A0%91%EC%88%98-02.jpg', 'image29.jpg', '2023-07-10 00:00:00', '2023-07-10 00:00:00'),
    ('HMG 드라이빙 익스피리언스 2024년 준비 기간 안내', '2024년 운영 개시 일정에 대해 안내드립니다.', 'https://drivingexperience.hyundai.co.kr/kr/image/2024/02/24%EB%85%84-%EC%9A%B4%EC%98%81-%EA%B3%B5%EC%A7%80%ED%8C%9D%EC%97%85PC.jpg', 'image30.jpg', '2024-01-01 00:00:00', '2024-01-01 00:00:00');

-- Program Entity
INSERT INTO program (name, category, level, qualification, estimated_duration, maximum_occupancy) VALUES
-- 1 ~ 5
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_1', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_2', 'HMG 드라이빙 익스피리언스 Level 1 이상 수료 (19~23년)', '총 190분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'LEVEL_3', 'HMG 드라이빙 익스피리언스 Level 2 이상 수료 (19~23년)', '총 260분', 4),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'N_ADVANCED', 'HMG 드라이빙 익스피리언스 Level 3 이상 수료 (19~23년)', '총 250분', 3),
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'N_MASTERS', 'HMG 드라이빙 익스피리언스 N 어드밴스드 이상 수료 (22년~23년)', '총 460분', 2),
-- 6 ~ 10
    ('DRIVING_EXPERIENCE', 'HYUNDAI', 'OFF_ROAD', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 90분', 2),
    ('DRIVING_EXPERIENCE', 'KIA', 'LEVEL_1', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'KIA', 'LEVEL_2', 'HMG 드라이빙 익스피리언스 Level 1 이상 수료(19~23년)', '총 190분', 4),
    ('DRIVING_EXPERIENCE', 'KIA', 'LEVEL_3', 'HMG 드라이빙 익스피리언스 Level 2 이상 수료(19~23년)', '총 260분', 4),
    ('DRIVING_EXPERIENCE', 'KIA', 'OFF_ROAD', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 90분', 2),
-- 11 ~ 15
    ('DRIVING_EXPERIENCE', 'GENESIS', 'LEVEL_1', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 120분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'LEVEL_2', 'HMG 드라이빙 익스피리언스 LEVEL 1 이상 수료 (19~23년)', '총 190분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'LEVEL_3', 'HMG 드라이빙 익스피리언스 LEVEL 2 이상 수료 (19~23년)', '총 260분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'DRIFT_LEVEL_1', 'HMG 드라이빙 익스피리언스 LEVEL 3 이상 수료 (19~23년)', '총 200분', 4),
    ('DRIVING_EXPERIENCE', 'GENESIS', 'DRIFT_LEVEL_2', 'HMG 드라이빙 익스피리언스 Drift Level 1 테스트 수료자 (20년~23년)', '총 220분', 4),
-- 16 ~ 20
    ('DRIVING_EXPERIENCE', 'GENESIS', 'OFF_ROAD', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '총 90분', 2),
    ('DRIVING_PLEASURE', 'TAXI', 'IONIQ_5N_TAXI', '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
    ('DRIVING_PLEASURE', 'TAXI', 'CIRCUIT_TAXI', '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
    ('DRIVING_PLEASURE', 'TAXI', 'CIRCUIT_RACE_TAXI', '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
    ('DRIVING_PLEASURE', 'TAXI', 'HIGH_SPEED_TAXI', '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
-- 21 ~ 25
    ('DRIVING_PLEASURE', 'TAXI', 'HIGH_SPEED_RACE_TAXI', '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
    ('DRIVING_PLEASURE', 'TAXI', 'DRIFT_TAXI', '남녀노소 누구나 (신장 140cm 이상)', '약 15분', 1),
    ('DRIVING_PLEASURE', 'TAXI', 'OFF_ROAD_TAXI', '남녀노소 누구나 (신장 130cm 이상)', '약 20분', 3),
    ('DRIVING_PLEASURE', 'HMG', 'TEST_DRIVE', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '약 150분', 4),
    ('DRIVING_PLEASURE', 'HMG', 'JUNIOR_DRIVING_EXPERIENCE', '만 7세 이상 초등학교 전 학년 (4학년 이상 권장)', '약 120분', 15),
-- 26 ~ 30
    ('DRIVING_PLEASURE', 'HMG', 'DRIVING_EXPERIENCE_CENTER_TOUR', '남녀노소 누구나 (신장 140cm 이상)', '약 20분', 4),
    ('DRIVING_PLEASURE', 'HMG', 'SCENIC_DRIVE', '국내/국제 유효 운전 면허 보유자 (신장 140cm 이상)', '약 120분', 4);

-- Course Entity
INSERT INTO course (name, detail, image_url, image_name) VALUES
-- 1 ~ 5
    ('다목적 주행 코스', '슬라럼, 짐카나 등 다양한 모듈로 구성되어 있는 코스로 드라이빙의 기본기부터 자동차의 가속 성능까지 종합적으로 경험할 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel1.jpg', 'course1.jpg'),
    ('제동 코스', '다양한 노면과 상황 속에서 브레이크의 성능을 최대로 이끌어 내는 코스로 위급 상황 시 안전하게 대처하는 테크닉을 익힐 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel2.jpg', 'course2.jpg'),
    ('마른 노면 서킷 B코스', '3.4km, 16개의 코너로 구성된 서킷에서 드라이빙 스킬을 종합적으로 경험할 수 있는 코스입니다. 레벨에 따라 두 개의 코스로 나누어 경험할 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel3.jpg', 'course3.jpg'),
    ('킥 플레이트 코스', '돌발 상황에 대처하는 능력을 키우는데 특화된 코스로 킥 플레이트의 인위적인 힘에 의해 불안정해진 차량을 안전하게 컨트롤하는 능력을 키울 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel6.jpg', 'course2.jpg'),
    ('마른 노면 서킷 A코스', '3.4km, 16개의 코너로 구성된 서킷에서 드라이빙 스킬을 종합적으로 경험할 수 있는 코스입니다. 레벨에 따라 두 개의 코스로 나누어 경험할 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel3.jpg', 'course3.jpg'),
-- 6 ~ 10
    ('젖은 노면 코스', '11개 코너, 1.6km의 젖은 서킷을 달리는 코스로 젖은 노면에서도 안전하게 자동차를 컨트롤하는 방법을 배울 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel5.jpg', 'course1.jpg'),
    ('고속주회로', '38.87도의 뱅크각의 4.6km 오벌 트랙에서 자동차의 한계 속도와 극한의 중력 가속도를 체험할 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel4.jpg', 'course2.jpg'),
    ('마른 노면 서킷 Full코스', '3.4km, 16개의 코너로 구성된 서킷에서 드라이빙 스킬을 종합적으로 경험할 수 있는 코스입니다. 레벨에 따라 두 개의 코스로 나누어 경험할 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel3.jpg', 'course3.jpg'),
    ('태안, 서산 일대 지방도로', '센터 외부로 나가 태안, 서산 일대 지방도로를 드라이빙하며 한국의 서해안 지역의 독특한 풍경과 함께 평화롭고 여유로운 드라이빙을 경험할 수 있습니다', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-dv-program2-comp-syn1.jpg', 'course3.jpg'),
    ('오프로드 코스', '보기만 해도 아찔한 구간를 통과하며 SUV의 내구성과 오프로드 성능을 체험하는 코스입니다. 언덕 경사, 모랫길, 계단 등 11가지 즐거운 장애물이 기다리고 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel8.jpg', 'course3.jpg'),
-- 10 ~ 11
    ('젖은 원선회 코스', '서로 다른 노면 위에서 눈으로만 보았던 드리프트를 실제로 경험해보는 코스입니다. 카운터 스티어링을 비롯한 고급 기술을 경험할 수 있습니다.', 'https://drivingexperience.hyundai.co.kr/kr/common/images/img-track-panel7.jpg', 'course3.jpg');

-- Comment Entity
INSERT INTO comment (user_id, program_id, content, created_at, updated_at) VALUES
-- Program 1
    ('userId1', 1, '평소에 자동차를 좋아해 현대 LV1 프로그램을 설레는 마음으로 참가하였습니다. LV1은 실제 운전하다가 겪을 수 있는 상황을 가정해 만든 구간들로 이루어졌다는 것을 알 수 있었습니다. LV1은 결과적으로 매우 만족스러웠구요. 바로 LV2를 신청하려고 생각 중에 있습니다. LV2는 아반떼 N을 더 깊이 체험해 볼 수 있다는 생각에 많은 기대를 하고 있습니다!', '2023-06-25 00:00:00', '2023-06-25 00:00:00'),
    ('userId2', 1, 'lv1 즐겁게 참여하였으며\n지인과 함께 참석하여 즐거운 시간을 가졌습니다.\n이제 lv2도 시작하려고 합니다.\n서킷 체험이 가장 인상에 남았으며 다음에는 더더욱 즐겁게 lv2로 도전해 보려고 합니다.', '2023-07-12 00:00:00', '2023-07-12 00:00:00'),
    ('userId3', 1, '와이프와 함께 방문해서 드라이빙 스킬업하는데 많은 도움이 되었습니다.\n꾸준히 신청해서 N Masters 그리고 드리프트까지 모두 듣고 싶습니다.', '2023-07-25 00:00:00', '2023-07-25 00:00:00'),
    ('userId4', 1, '제 인생 취미가 돼버렸네요. 최고입니다.', '2023-07-27 00:00:00', '2023-07-27 00:00:00'),
    ('userId5', 1, '운전의 맛을 새롭게 느낄 수 있었습니다.\n계속해서 스킬업해 필드에서 있을 수 있는 위험회피 능력과 섯킷 주행을 통한 드라이빙의 즐거움을 만끽하고 싶습니다.', '2023-07-29 00:00:00', '2023-07-29 00:00:00'),
    ('userId6', 1, '좋은 시설, 좋은 내용이었습니다 운전자라면 경험 해보시길 추천합니다.\n물론 예약하기가 너무 힘든건 ... ㅎㅎ', '2023-08-04 00:00:00', '2023-08-04 00:00:00'),
    ('userId7', 1, '오늘 레벨1 무사히 이수완료했습니다!\n권봄이 익스트럭터님의 완벽한 지도하에 잘 할 수 있었던 것 같습니다^^\n다음에 레벨2도 도전하고 싶은 욕심도 생겼습니다^^\n정말정말 감사했습니다!!', '2023-08-11 00:00:00', '2023-08-11 00:00:00'),
    ('userId8', 1, '이렇게 한걸음 더 나아갔습니다.', '2023-08-14 00:00:00', '2023-08-14 00:00:00'),
    ('userId9', 1, '7월달에 남자친구랑 1주년 기념으로 레벨1 들었었는데 너무 재미있었어요!\n다음 기념일에 레벨2 들으러 또 올거에요!', '2023-08-22 00:00:00', '2023-08-22 00:00:00'),
    ('userId10', 1, '레벨 1이어도 배울게 상당히 많네요. 다음 기회에 또 찾아오고싶습니다.', '2023-09-08 00:00:00', '2023-09-08 00:00:00'),

    ('userId2', 2, '저는 그저 운전석에 앉은 한마리 침팬지에 불과합니다.', '2023-09-08 00:00:00', '2023-09-08 00:00:00'),
    ('userId4', 3, '좋은 경험이었습니다! 돈이 아깝지 않아요. ㅎㅎ', '2023-09-08 00:00:00', '2023-09-08 00:00:00'),
    ('userId5', 4, '매번 새로움을 경험할 수 있다는 게 즐겁고 신기합니다. 다음에 또 오겠습니다~ ㅎㅎ', '2023-09-08 00:00:00', '2023-09-08 00:00:00'),
    ('userId6', 3, '즐거웠습니다. 어서 2024년 일정이 오픈되었으면 좋겠습니다.', '2023-11-23 00:00:00', '2023-11-23 00:00:00');

-- ProgramCourse Entity
INSERT INTO program_course (program_id, course_id) VALUES
-- HYUNDAI DRIVING_EXPERIENCE
    (1, 1), (1, 2), (1, 3),
    (2, 1), (2, 4), (2, 5),
    (3, 1), (3, 6), (3, 8), (3, 7), (3, 11),
    (4, 2), (4, 8),
    (5, 6), (5, 7), (5, 8),
    (6, 10),
-- KIA DRIVING_EXPERIENCE
    (7, 1), (7, 2), (7, 3),
    (8, 1), (8, 7), (8, 4), (8, 5),
    (9, 1), (9, 6), (9, 8), (9, 7), (9, 11),
    (10, 10),
-- GENESIS DRIVING_EXPERIENCE
    (11, 1), (11, 2), (11, 3),
    (12, 1), (12, 4), (12, 5),
    (13, 1), (13, 6), (13, 8), (13, 7), (13, 11),
    (14, 11),
    (15, 6), (15, 11),
    (16, 10),
-- TAXI DRIVING_PLEASURE
    (17, 1), (17, 8),
    (18, 8),
    (19, 8),
    (20, 7),
    (21, 7),
    (22, 6), (22, 11),
    (23, 10),
-- HMG DRIVING_PLEASURE
    (24, 1), (24, 3), (24, 9),
    (25, 8), (25, 10),
    (26, 1), (26, 2), (26, 4), (26, 6), (26, 7), (26, 8), (26, 10), (26, 11),
    (27, 9);

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
-- Program: 1
    ('아반떼 N Line', '가솔린 1.6 터보', '204 PS / 6,000 RPM', '27 kgf•m / 1,500 ~ 4,500 RPM'),
-- Program: 2, 3, 4, 24, 25
    ('아반떼 N DCT', '가솔린 N전용 2.0 터보', '280 PS / 5,500 ~ 6,000 RPM', '40 kgf•m / 2,100 ~ 4,700 RPM'),
-- Program: 5, 18, 19, 20, 21
    ('아반떼 N DCT R-Tune', '가솔린 N전용 2.0 터보', '280 PS / 5,500 ~ 6,000 RPM', '40 kgf•m / 2,100 ~ 4,700 RPM'),
-- Program: 7, 25, 26
    ('EV6 GT-Line', '롱레인지 4WD', '239 kW / 325 PS', '605 Nm / 61.7 kgf•m'),
-- Program: 11
    ('G70 2.0T AWD', '가솔린 2.0 터보 AWD', '252 PS / 6,200 RPM (스포츠패키지)', '36 kgf•m / 1,400 ~ 4,000 RPM'),
-- Program: 3, 17
    ('아이오닉 5 N', 'EV AWD', '478 kW / 650 PS', '770 Nm / 78.5 kgf•m'),
-- Program: 24
    ('아이오닉 6', '롱레인지 20인치 HTRAC', '239 kW / 325 PS', '605 Nm / 61.7 kgf•m'),
-- Program: 8, 9, 24
    ('EV6 GT', 'GT 4WD', '430 kW / 585 PS', '740 Nm / 75.5 kgf•m'),
-- Program: 24
    ('G70 슈팅 브레이크', '가솔린 2.0 터보 AWD (스포츠패키지)', '255 PS / 6,200 RPM', '36.0 kgf•m / 4,000 RPM'),
-- Program: 6
    ('투싼 2.0D HTRAC', '디젤 2.0 터보 HTRAC', '184 PS / 4,000 RPM', '42.5 kgf•m / 2,000 ~ 2,750 RPM'),
-- Program: 6, 23, 25
    ('팰리세이드 2.0D HTRAC', '디젤 2.2 터보 HTRAC', '202 PS / 3,800 RPM', '45 kgf•m / 1,750 ~ 2,750 RPM'),
-- Program: 10
    ('스포티지 2.0D 4WD', '디젤 2.0 터보 4WD', '184 PS / 4,000 RPM', '42.5 kgf•m / 2,000 ~ 2,750 RPM'),
-- Program: 10, 23, 25
    ('모하비 3.0D 4WD', '디젤 V6 3.0 터보 4WD', '257 PS / 3,800 RPM', '57.1 kgf•m / 1,500 ~ 3,000 RPM'),
-- Program: 12, 13
    ('G70 3.3T AWD', '가솔린 V6 3.3 트윈터보 AWD (스포츠패키지)', '373 PS / 6,000 RPM', '52 kgf•m / 1,300 ~ 4,500 RPM'),
-- Program: 12, 13, 14, 15, 22
    ('G70 3.3T RWD', '가솔린 V6 3.3 트윈터보 AWD (스포츠패키지)', '373 PS / 6,000 RPM', '52 kgf•m / 1,300 ~ 4,500 RPM'),
-- Program: 16
    ('GV70 2.2D AWD', '디젤 2.2 터보 AWD', '202 PS / 3,800 RPM', '45 kgf•m / 1,750 ~ 2,750 RPM'),
-- Program: 23, 25
    ('GV80 3.0D AWD', '디젤 직렬 6기통 3.0 터보 AWD', '273 PS / 3,800 RPM', '60 kgf•m / 1,500 ~ 3,000 RPM'),
-- Program: 18, 20, 21
    ('EV6 GT R-Tune', 'GT 4WD', '430 kW / 585 PS', '740 Nm / 75.5 kgf•m'),
-- Program: 26
    ('아이오닉 5', '롱레인지 프레스티지 HTRAC', '225 kW / 305 PS', '605 Nm / 61.7 kgf•m'),
-- Program: 27
    ('그랜저(2023)', '캘리그래피 가솔린 3.5 4WD', '300 PS / 6,400 RPM', '36.6 kgf•m / 5,000 RPM'),
-- Program: 27
    ('K8', '플래티넘 가솔린 3.5 4WD', '300 PS / 6,400 RPM', '36.6 kgf•m / 5,000 RPM');


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
    (12, '2024-03-17 16:00:00', '2024-03-29 16:00:00', '2024-03-16 18:00:00'),

    -- 차량 종류 여러 개인 프로그램 수업
    (18, '2024-03-17 16:00:00', '2024-03-29 16:00:00', '2024-03-16 18:00:00');

-- ClassCar Entity

INSERT INTO class_car (driving_class_id, maximum_occupancy, car_id, cost) VALUES
-- 3월 2일
    (1, 4, 1, 90000),
    (2, 4, 8, 300000),
    (3, 4, 2, 180000),
    (4, 1, 12, 40000), (4, 1, 13, 50000),
-- 3월 3일
    (5, 3, 2, 280000), (5, 3, 6, 350000), --최대 4인
    (6, 4, 5, 120000), -- 4자리중 3자리 예약
    (7, 3, 2, 450000), -- 3자리중 2자리 예약
    (8, 3, 14, 240000), (8, 3, 15, 240000),

-- 차량 종류 여러 개인 프로그램 수업
    (25, 4, 2, 100000),
    (25, 2, 7, 100000),
    (25, 3, 8, 100000),
    (25, 4, 9, 100000);

-- Participation Entity
INSERT INTO participation (user_id, class_car_id, completion, participants, paid_at, status) VALUES
--3월 2일
    ('userId10', 1, false, 2, '2024-02-14 10:04:30', 'PAID'),
    ('userId8', 1, false, 2, '2024-02-14 10:53:23', 'PAID'),
    ('userId7', 2, false, 1, '2024-02-14 17:23:32', 'PAID'),
    ('userId5', 2, false, 3, '2024-02-14 18:02:03', 'PAID'),
    ('userId9', 3, false, 1, '2024-02-14 17:05:03', 'PAID'),
    ('userId4', 3, false, 1, '2024-02-14 18:05:03', 'PAID'),
    ('userId1', 3, false, 2, '2024-02-14 19:01:30', 'PAID'),
    ('userId2', 4, false, 1, '2024-02-14 17:01:35', 'PAID'),
    ('userId3', 5, false, 1, '2024-02-14 18:02:00', 'PAID'),
--3월 3일
    ('userId10', 6, false, 3, '2024-02-15 10:04:30', 'PAID'),
    ('userId8', 7, false, 1, '2024-02-15 10:53:23', 'PAID'),
    ('userId7', 8, false, 2, '2024-02-15 17:23:32', 'PAID'),
    ('userId5', 8, false, 1, '2024-02-15 18:02:03', 'PAID'),
    ('userId9', 9, false, 1, '2024-02-15 17:05:03', 'PAID'),
    ('userId4', 9, false, 1, '2024-02-15 18:05:03', 'PAID'),
    ('userId1', 10, false, 2, '2024-02-15 19:01:30', 'PAID'),
    ('userId2', 11, false, 2, '2024-02-15 17:01:35', 'PAID');


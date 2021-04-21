-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2021 at 10:11 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electronicstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Processor'),
(2, 'Motherboard'),
(3, 'Desktop'),
(4, 'Laptop & Notebooks'),
(53, 'PSU - Nguồn máy tính');

-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

CREATE TABLE `contacts` (
  `id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `num_phone` int(11) NOT NULL,
  `address` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`id`, `name`, `email`, `num_phone`, `address`, `message`) VALUES
(11, 'tưởng', 'vantuong@gmail.com', 123456, '123, lý thường kiệt', 'qua tốt'),
(12, 'Yên', 'yenvo@gmail.com', 123456789, '24, hoàng hoa thám', 'qua đỉnh'),
(13, 'Phin', 'vanphin@gmail.com', 12345326, '123, lý nam đế', 'qua tốt'),
(14, 'Phương', 'vantuong123@gmail.com', 123456, '123, tạ hiền', 'qua tốt');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `username` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `username`, `password`, `fullname`, `phone`, `address`) VALUES
(5, 'kimty', '202cb962ac59075b964b07152d234b70', 'võ thị kim ty', '123456', 'xóm tân'),
(13, 'yenv', '202cb962ac59075b964b07152d234b70', 'tưởng', '123456', '185 Văn Tiến Dũng, Cẩm Lệ, ĐN'),
(14, 'thiet', '202cb962ac59075b964b07152d234b70', 'thiết', '123456', '185 Văn Tiến Dũng, Cẩm Lệ, ĐN'),
(15, 'phuong', '202cb962ac59075b964b07152d234b70', 'vo tri phương', '123456', 'xóm tân');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `name`, `phone`, `address`) VALUES
(23, 'võ thị kim ty', '123456', 'xóm tân'),
(24, 'võ thị kim ty', '123456', 'xóm tân'),
(25, 'vo tri phương', '123456', 'xóm tân'),
(26, 'tưởng', '123456', '185 Văn Tiến Dũng, Cẩm Lệ, ĐN'),
(34, 'quốc', '123456', 'hòa khánh'),
(38, 'võ thị kim ty', '123456', 'xóm tân');

-- --------------------------------------------------------

--
-- Table structure for table `producer`
--

CREATE TABLE `producer` (
  `id` int(11) NOT NULL,
  `producer_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `producer`
--

INSERT INTO `producer` (`id`, `producer_name`) VALUES
(1, 'ACER'),
(2, 'ASUS'),
(3, 'DELL'),
(4, 'HP'),
(6, 'Lenovo'),
(7, 'MSI'),
(11, 'GIGABYTE');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` float NOT NULL,
  `old_price` int(11) NOT NULL,
  `picture` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_create` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `counter` int(11) NOT NULL DEFAULT 0,
  `detail` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `producer_id` int(11) NOT NULL,
  `cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `old_price`, `picture`, `date_create`, `counter`, `detail`, `producer_id`, `cat_id`) VALUES
(17, 'Mainboard ASUS Z590 ROG MAXIMUS XIII EXTREME GLACIAL ', 40990000, 52000000, '2-515890404804200.jpg', '2021-03-04 02:01:59', 0, '-Chipset: Intel Z590\r\n-Socket: LGA1200\r\n-Kích thước: E-ATX\r\n-Số khe RAM: 4\r\n-Tích hợp block tản nhiệt nước', 2, 2),
(18, 'Mainboard ASUS Z590 ROG MAXIMUS XIII EXTREME ', 24290000, 25290000, '2-1741547786828600.jpg', '2021-03-02 12:07:15', 2, '-Chipset: Intel Z590\r\n-Socket: LGA1200\r\n-Kích thước: E-ATX\r\n-Số khe RAM: 4', 2, 2),
(19, 'Mainboard ASUS Z590 ROG MAXIMUS XIII HERO ', 1000, 1022, '3-1742096569901100.jpg', '2021-02-24 08:11:02', 1, '-Bo mạch chủ cao cấp chipset Intel Z590 của Asus\r\n-Kích thước ATX\r\n-4 Khe Ram', 2, 2),
(20, 'Mainboard ASUS ROG STRIX Z590-E GAMING WIFI', 995, 1010, '4-1742155730064900.jpg', '2021-03-02 13:14:06', 2, '-Chipset: Intel Z590\r\n-Socket: LGA1200\r\n-Kích thước: ATX\r\n-Số khe RAM: 4', 2, 2),
(21, 'Mainboard ASUS ROG STRIX Z590-F GAMING WIFI', 890, 900, '5-1742225072788400.jpg', '2021-03-03 12:45:46', 3, '-Chipset: Intel Z590\r\n-Socket: LGA1200\r\n-Kích thước: ATX\r\n-Số khe RAM: 4', 2, 2),
(32, 'Nguồn Corsair RM Series RM750 - 750W (80 Plus Gold Certified Full Modular/Màu Đen)', 500, 560, '14-515874998155200.jpg', '2021-03-04 02:01:43', 1, '-Chứng nhận 80 PLUS Gold: hoạt động hiệu quả , tiết kiệm điện năng, ít tiếng ồn và nhiệt độ mát hơn.\r\n-Tự điều chỉnh tiếng ồn khi hoạt động: quạt 140mm với đường cong quạt được tính toán đặc biệt đảm bảo tiếng ồn khi hoạt động được giữ ở mức tối thiểu, ngay cả khi hoạt động tối đa công suất\r\n-Sử dụng tụ điện 105 ° C: tụ điện cấp công nghiệp cho hiệu suất cao và hoạt động ổn định\r\n-Tương thích với chế độ chờ mới nhất của Microsoft: thời gian thức dậy cực nhanh và hiệu quả tải thấp tốt hơn.\r\n-Chế độ quạt Zero RPM: ở mức tải thấp và trung bình Quạt làm mát tắt hoàn toàn cho hoạt động gần như im lặng.', 11, 53),
(33, 'Nguồn Jetek M500 500W (Màu Đen/Led RGB )', 400, 420, '13-515861378206500.jpg', '2021-03-04 02:01:30', 0, '-Hiệu suất cao lên tới 80%\r\n-Thiết kế cấu truc tổ ong, tối ưu luồng gió lưu thông làm mát\r\n-Ngõ ra điện áp +12V tăng cường công suất, mở rộng khả năng sử dụng\r\n-Quạt làm mát 12cm đem lại sự yên tĩnh khi hoạt động\r\n-DC to DC Converter\r\n-Đèn led RGB tích hợp độc đáo', 11, 53),
(34, 'CPU Intel Xeon Silver 4110 (2.1GHz turbo up to 3.0GHz, 8 nhân, 16 luồng, 11MB Cache, 85W) - Socket Intel LGA 3647', 1200, 1250, '15-515845293791800.jpg', '2021-03-04 02:01:14', 0, 'Dòng sản phẩm chuyên biệt dành cho server/máy trạm\r\n8 nhân & 16 luồng\r\nXung nhịp: 2.1GHz (Cơ bản) / 3.0GHz (Boost)\r\nSocket: LGA 3647\r\nHỗ trợ RAM ECC\r\nKhông kèm quạt tản nhiệt từ hãng\r\nKhông tích hợp sẵn iGPU', 7, 1),
(35, 'CPU Intel Core i9-9900KF (3.6GHz turbo up to 5.0GHz, 8 nhân 16 luồng, 16MB Cache, 95W) - Socket Intel LGA 1151-v2', 1200, 1300, '14-515832149088900.jpg', '2021-03-07 04:50:50', 4, 'Phiên bản cắt giảm đi nhân đồ họa tích hợp của 9900K\r\nSố nhân: 8\r\nSố luồng: 16\r\nTốc độ cơ bản: 3.6 GHz\r\nTốc độ tối đa: 5.0 GHz\r\nCache: 16MB\r\nTiến trình sản xuất: 14nm', 11, 1),
(36, 'CPU Intel Xeon E-2136 (3.3GHz turbo up to 4.5GHz, 6 nhân, 12 luồng, 12MB Cache, 80W) - Socket Intel LGA 1151-v2', 500, 800, '17-515817964328900.jpg', '2021-03-04 02:00:46', 1, 'Dòng sản phẩm chuyên biệt dành cho máy trạm giá rẻ\r\nPhù hợp cho các phần mềm render, thiết kế\r\n6 nhân & 12 luồng\r\nXung nhịp: 3.3 GHz (Cơ bản) / 4.5 GHz (Boost)\r\nSocket: LGA 1151v2 (C246)\r\nHỗ trợ RAM ECC\r\nKhông tích hợp sẵn iGPU', 7, 1),
(37, 'Máy bộ Dell VOSTRO 3670MT (MTG5420) (G5420/4G/1TB/RW/K/M/WL/BT)', 700, 760, '19-515801482928300.jpg', '2021-03-04 02:00:30', 0, 'Intel Pentium Gold G5420 3.80 GHz, 4MB\r\n\r\nIntel B360 Chipset\r\n\r\n1xDDR4 4GB 2666Mhz (2 slot, Max 32GB)\r\n\r\nHDD 1TB SATA 7200rpm (co khe SSD M.2 NVMe Up to 512GB)\r\n\r\nTray load DVD-RW\r\n\r\nDell Wireless 1707 Card (802.11bgn + Bluetooth 4.0)\r\n\r\n1xLAN Gigabit, USB 3.1 x2 (front); USB 2.0 x4 ; VGAx1; HDMIx1; Card Reader\r\n\r\nBảo hành 12 tháng', 3, 3),
(38, 'Máy tính để bàn lắp ráp Phong Vũ (I5-10400 Comet Lake-S/B460/8GB RAM/ SSD 256GB M2 + HDD 1TB/VGA1650 4GB)', 800, 890, '18-515779206749000.jpg', '2021-03-07 03:51:25', 3, '* CPU: INTEL Core i5-10400 (6C/12T, 2.90 GHz Up to 4.30 GHz, 12MB) – 1200\r\n\r\n* Mainboard:    MAINBOARD GIGABYTE B460M DS3H\r\n\r\n* RAM: DDR4 8GB/ tản nhiệt\r\n\r\n* HDD: 1TB SATA\r\n\r\n* SSD :    SSD 256GB M2 KINGMAX PCI PQ3480\r\n\r\n* Keyboard + Mouse :  DAREU LK135 GAMING (190K)  + MouseDAREU LM130 (140K)\r\n\r\n* VỎ CASE  XIGMATEK HERO ( 3 FAN )\r\n\r\n* NGUỒN :  CORSAIR 550W VS550 (FAN 12CM BLACK)\r\n\r\n* VGA CARD :  CARD VGA GTX1650 4GB ASUS\r\n\r\n* Tản nhiệt Deepcool GAMMAXX400 v2', 4, 3),
(39, 'Máy bộ PC Dell Inspiron 3881 (Core i5 – 10400/ 8Gb/SSD 512 PCIe NVMe/Non DVD/Win10 SL)', 760, 780, '20-515765121777600.jpg', '2021-03-04 01:59:53', 4, 'Intel Core i5 – 10400 ( up to 4.7 Ghz ),\r\n\r\nRAM : 1 x 8Gb DDR4 2666Mhz,\r\n\r\nSSD 512 PCIe NVMe,\r\n\r\nNon DVD,\r\n\r\nKết nối mạng: Wlan + Bluetooth\r\n\r\nHệ điều hành: Windows 10 Home SL English\r\n\r\nBảo hành 12 tháng (PSD-18/11/20)', 6, 3),
(40, 'Lenovo Thinkpad T460S', 1250, 1300, '23-515748805599600.png', '2021-03-04 01:59:37', 1, 'CPU: Core i5 or i7\r\nRAM: 8GB or 16GB\r\nỔ cứng: SSD 256GB or 512GB\r\nMàn hình: 14 inch FHD (1920 x 1080)\r\nCard màn hình: Share Intel HD Graphics 520\r\nCổng kết nối: 3xUSB 3.0 Gen 1, 1 HDMI, 1 RJ-45\r\nHệ điều hành: Win10 Pro 64bit\r\nThiết kế: Màu đen, màn hình vuông, sử dụng các vật liệu nhẹ và cao cấp.\r\nKích thước: 331 x 226.8 x 16.9 - 18.8 mm\r\nThời điểm ra mắt: 2016', 6, 4),
(41, 'Laptop Asus D509DA-EJ800T (R3 3250U/4GB RAM/256GB SSD/15.6 FHD/Win10/Bạc)', 1400, 1500, '21-515733378079800.jpg', '2021-03-04 01:59:22', 2, 'Chủng loại: Vivobook\r\nPart Number\r\n90NB0P51-M14900\r\nMầu sắc\r\nTransparent Silver\r\nBộ vi xử lý\r\nAMD Ryzen™ 3 3250U Processor 2.6 GHz (4M Cache, up to 3.5 GHz, 2 cores)\r\nChipse\r\nAMD Radeon™ Graphics\r\nBộ nhớ trong\r\n4GB DDR4 on board\r\n\r\nSố khe cắm\r\n0\r\nDung lượng tối đa\r\n1x DDR4 SO-DIMM slot\r\n\r\n1x M.2 2280 PCIe 3.0x2\r\n1x STD 2.5” SATA HDD\r\nVGA\r\n\r\n \r\n\r\nỔ cứng\r\n\r\n256GB M.2 NVMe™ PCIe® 3.0 SSD\r\nN/A\r\nỔ quang\r\nN/A\r\n\r\nCard Reader\r\nBảo mật, Công nghệ\r\nFingerPrint\r\nMàn hình:\r\n15.6-inch, FHD (1920 x 1080) 16:9 , NTSC: 45%, Brightness : 200 nits, LED Backlit.', 2, 4),
(42, 'Ice Blue Surface Laptop Go 12.4\'\' i5-1035G1 8GB 256GB SSD', 2000, 2300, '23-515720007735300.png', '2021-03-04 01:59:08', 6, '- CPU: Intel Core i5-1035G1 thế hệ thứ 10, 4 lõi, tốc độ 1.0 GHz\r\n- GPU: Intel UHD Graphics tích hợp\r\n- RAM: LPDDR4x 8GB\r\n- Ổ cứng: SSD 256GB\r\n- Màn hình: 12.4\" 1536 x 1024 PixelSense, cảm ứng, 148 PPI\r\n- Camera: 720p HD f2.0\r\n- Cổng giao tiếp: 1 x USB-C, 1 x USB-A, 1 x Surface Connect\r\n- Kết nối không dây: Wi-Fi 6 (802.11ax), Bluetooth 5.0\r\n- Thời lượng sử dụng pin lên đến 13 giờ\r\n- Hệ điều hành: Windows 10 Home (chế độ S)', 3, 4),
(43, 'Dell Vostro 14 3400 Core i3-1115G4 Mới ra mắt', 1200, 1250, '22-515709432021600.jpg', '2021-03-04 01:58:58', 8, 'Cân nặng:	1.59KG\r\nCPU:	11th Generation Intel Core i3-1115G4 Tiger Lake (3.0GHz Up to 4.10 GHz, 2 Cores, 4 Threads, 6MB Cache, 4 GT/s)\r\nMàu sắc:	70235020 - Black\r\nTình Trạng Máy :	Mới 100%\r\nXuất xứ:	Chính Hãng\r\nRAM:	8GB DDr4 Bus 2666Mhz + Dư 1 khe để gắn Ram\r\nỔ cứng:	256GB SSD M.2 PCie +  Dư 1 khe gắn HDD Sata/PCie\r\nMàn hình:	14.0 inch FHD (1920 x 1080) Anti-glare LED Backlight Non-Touch Narrow Border WVA Display\r\nVGA/GPU:	Intel UHD Graphics', 3, 4),
(45, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 1250, '21-515698936320100.jpg', '2021-03-04 02:52:07', 5, '-Hãng sản xuất: Acer\r\n-Chủng loại: Aspire 3 A315-57G-31YD\r\n-Part Number: NX.HZRSV.008\r\n-Màu sắc: Đen\r\n-Bộ vi xử lý: Intel® Core™ i3-1005G1 (1.2Ghz/4MB cache)\r\n-Chipset: Intel\r\n-Bộ nhớ trong: 4GB onboard\r\n-Số khe cắm: 1\r\n-Dung lượng tối đa: 20GB\r\nVGA: Nvidia Geforce MX 330 2G DDR5\r\n-Ổ cứng: 256GB PCIe NVMe SSD\r\n(nâng cấp tối đa 2TB HDD và 512SSD PCIe NVMe)\r\n-Ổ quang: None\r\n-Card Reader: None\r\n-Bảo mật, công nghệ : BIOS user, supervisor, HDD passwords, Kensington lock slot; bản lề mở 180o\r\n-Màn hình: 15.6 inch FHD Acer ComfyView LED LCD\r\n-Webcam: HD\r\n-Audio: Two built-in stereo speakers; Built-in digital microphone\r\n-Giao tiếp mạng: Gigabit LAN\r\n-Giao tiếp không dây: Intel® Wireless-AC 9461/9462, 802.11a/b/g/n/ac wireless LAN, 1x1 MU-MIMO technology, Bluetooth® 5.0\r\n-Cổng giao tiếp: 2x USB 3.1, 1 x USB 2.0, 1 x HDMI,1 x Ethernet, 1 x 3.5 mm Headphone /speaker jack, 1 x DC Jack\r\n-Pin: 3-cell (36Whr)\r\n-Kích thước (rộng x dài x cao): 363.4 (W) x 247.5 (D) x 19.9 (H) mm\r\n-Cân nặng: 1.9kg\r\n-Hệ điều hành: Win 10 Home\r\n-Phụ kiện đi kèm: Cable + Sạc', 1, 4),
(46, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 1340, '11-515586732068300.jpg', '2021-03-09 12:12:56', 16, '-Hãng sản xuất: Acer\r\n-Chủng loại: TC-865\r\n-Part Number: DT.BARSV.00B\r\n-Kiểu dáng: Bộ vi xử lý Core i5 9400\r\n-Chipset: Intel® B360\r\n-Bộ nhớ trong: 4GB DDR4 2666Mhz\r\n-Số khe cắm: 2\r\n-Dung lượng tối đa: 32GB\r\n-VGA: UHD 630\r\n-Ổ cứng: 1TB 7200RPM\r\n-Giao tiếp mạng: Gigabit LAN\r\n-Khe cắm mở rộng: 1 x M.2 slot for SSD\r\n1 x M.2 slot for WLAN\r\n1 x PCIe x16 slot\r\n1 x PCIe x1 slot\r\n-Cổng giao tiếp: Front:\r\n1 x Card reader\r\n1 x USB 3.1 Type-C\r\n1 x USB 3.1 Gen2 Type-A\r\n1 x Audio jack (combo)\r\n ', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `showorders`
--

CREATE TABLE `showorders` (
  `id` int(11) NOT NULL,
  `id_orders` int(11) NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` float NOT NULL,
  `qty` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `showorders`
--

INSERT INTO `showorders` (`id`, `id_orders`, `name`, `price`, `qty`) VALUES
(22, 23, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 1),
(23, 23, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 1),
(24, 24, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 2),
(25, 24, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 1),
(26, 24, 'Ice Blue Surface Laptop Go 12.4\'\' i5-1035G1 8GB 256GB SSD', 2000, 1),
(27, 24, 'Laptop Asus D509DA-EJ800T (R3 3250U/4GB RAM/256GB SSD/15.6 FHD/Win10/Bạc)', 1400, 1),
(28, 25, 'ram', 123, 1),
(29, 25, 'Nguồn Jetek M500 500W (Màu Đen/Led RGB )', 400, 3),
(30, 25, 'Máy bộ PC Dell Inspiron 3881 (Core i5 – 10400/ 8Gb/SSD 512 PCIe NVMe/Non DVD/Win10 SL)', 760, 2),
(31, 26, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 2),
(32, 26, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 3),
(33, 27, 'Ice Blue Surface Laptop Go 12.4\'\' i5-1035G1 8GB 256GB SSD', 2000, 1),
(34, 27, 'Laptop Asus D509DA-EJ800T (R3 3250U/4GB RAM/256GB SSD/15.6 FHD/Win10/Bạc)', 1400, 2),
(35, 28, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 6),
(36, 28, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 2),
(37, 29, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 7),
(38, 29, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 5),
(39, 30, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 6),
(40, 30, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 5),
(41, 30, 'Laptop Asus D509DA-EJ800T (R3 3250U/4GB RAM/256GB SSD/15.6 FHD/Win10/Bạc)', 1400, 1),
(42, 31, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 6),
(43, 31, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 5),
(44, 31, 'Laptop Asus D509DA-EJ800T (R3 3250U/4GB RAM/256GB SSD/15.6 FHD/Win10/Bạc)', 1400, 1),
(45, 32, 'PC Acer TC-865 (i5-9400/4GB RAM/1TB HDD/DVDRW/K+M/Endless OS) (DT.BARSV.00B)', 1300, 6),
(46, 32, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 7),
(47, 32, 'Laptop Asus D509DA-EJ800T (R3 3250U/4GB RAM/256GB SSD/15.6 FHD/Win10/Bạc)', 1400, 1),
(48, 32, 'Ice Blue Surface Laptop Go 12.4\'\' i5-1035G1 8GB 256GB SSD', 2000, 5),
(60, 38, 'Laptop Acer Aspire A315-57G-31YD (NX.HZRSV.008) (i3 1005G1/4GB RAM/256GB SSD/MX330 2G/15.6 inch FHD/Win 10/Đen)', 1200, 3),
(61, 38, 'Dell Vostro 14 3400 Core i3-1115G4 Mới ra mắt', 1200, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `num_phone` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` text COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fullname`, `num_phone`, `address`) VALUES
(15, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'Võ Văn Tưởng', '123456', 'quảng nam'),
(16, 'yenvu', 'e10adc3949ba59abbe56e057f20f883e', 'Võ Yên', '234567', 'đà nẵng'),
(18, 'IsPhin', 'e10adc3949ba59abbe56e057f20f883e', 'Võ Văn Phin', '456789', 'sài gòn'),
(23, 'Thietnguyen', '202cb962ac59075b964b07152d234b70', 'Nguyễn Phước Thiết', '123456789', '185 Văn Tiến Dũng, Cẩm Lệ, ĐN'),
(24, 'kimty', 'e10adc3949ba59abbe56e057f20f883e', 'quoc', '0123456789', 'xóm tân');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `message` (`id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `producer`
--
ALTER TABLE `producer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `showorders`
--
ALTER TABLE `showorders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT for table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `producer`
--
ALTER TABLE `producer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT for table `showorders`
--
ALTER TABLE `showorders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

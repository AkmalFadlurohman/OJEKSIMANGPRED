# Tugas 2 IF3110 Pengembangan Aplikasi Berbasis Web

## Penjelasan
1. Basis data pada sistem ada dua. yang pertama "ojeksimangpred_IDServices" dan "ojeksimangpred_OjolServices". Setiap ojo
   a. Basis Data ojeksimangpred_IDServices menangani manajemen akun pengguna, baik sebagai driver, customer maupun token user. Isi dari basis data ini adalah:
    *user*
    ├── id 
    ├── name
    ├── email
    ├── phone
    ├── username
    ├── password
    ├── status
    ├── token
    ├── secret
    └── pict
    *driver*
    ├── driver_id 
    ├── total_score
    └── votes
   b. Basis Data ojeksimangpred_OjolServices menangani manajemen segala hal yang berkaitan dengan order serta profil driver. Isi dari basis data ini adalah:
    *driver_prefloc*
    ├── driver_id 
    └── pref_loc
    *order*
    ├── order_id 
    ├── dest_city
    ├── pick_city
    ├── score
    ├── comment
    ├── driver_id
    ├── cust_id
    ├── date
    ├── customer_visibility
    └── driver_visibility
2. Konsep *shared session* pada REST ialah server tidak menyimpan state apapun mengenai client. Seluruh state disimpan pada client dan akan diproses saat client ingin memperoleh data dari server. Hal ini biasanya disebut dengan istilah *Stateless*.
3. Token pada program web yang kami buat menggunakan library *Java Web Token* JWT, Token digenerate saat login lalu dimasukkan ke dalam tabel *user* pada database *ojeksimangpred_IDServices*. Saat user melakukan logout, nilai token dari user yang logout akan di set menjadi NULL. Token akan expired setelah user melakukan login selama 60 menit, token akan secara otomatis di set menjadi NULL dan user akan langsung kembali pada login page.
4. Kelebihan arsitekturini dibandingkan arsitektur monolitik adalah pembagian tugas nya yang jelas. Jadi setiap bagian memiliki tanggung jawab sendiri sendiri sehingga dapat meningkatkan performa/kinerja dari sistem dan memudahkan dalam mendebug program. Arsitektur ini juga memiliki keamanan yang lebih susah ditembus karena server side nya tidak terpusat dan tersebar.

 Kekurangan dari arsitektur ini adalah basis datanya yang terpartisi pada lebih dari satu server, dan deployment serta testing yang lebih sulit daripada arsitektur monolotik.

## Pembagian Tugas
"Gaji buta dilarang dalam tugas ini. Bila tak mengerti, luangkan waktu belajar lebih banyak. Bila belum juga mengerti, belajarlah bersama-sama kelompokmu. Bila Anda sekelompok bingung, bertanyalah (bukan menyontek) ke teman seangkatanmu. Bila seangkatan bingung, bertanyalah pada asisten manapun."

*Harap semua anggota kelompok mengerjakan SOAP dan REST API kedua-duanya*. Tuliskan pembagian tugas seperti berikut ini.

Login : Akmal Fadlurohman
Register : Akmal Fadlurohman
history : Akmal Fadlurohman
Profile : Akmal Fadlurohman
Order : Radiyya Dwisaputra
handler kelas : M. Ferdi Ghozali
Database : Akmal Fadlurohman

Dosen : Yudistira Dwi Wardhana | Riza Satria Perdana | Muhammad Zuhri Catur Candra
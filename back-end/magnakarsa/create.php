<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    // username	password	login_time	login_state	

    $username = $_POST["username"];
    $password = $_POST["password"];
    // $login_time = $_POST["login_time"];
    // $login_state = $_POST["login_state"];

    $perintah = "INSERT INTO User (username, password, login_time, login_state) VALUES('$username','$password',now(), 'Tidak Aktif')";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek      = mysqli_affected_rows($konek);

    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Simpan Data Berhasil";
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Menyimpan Data";
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);

<?php
require("koneksi.php");

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){

    $username = $_POST["username"];
    $login_time = $_POST["login_time"];
    $login_state = $_POST["login_state"];

    // $perintah = "DELETE FROM tbl_laundry WHERE id = '$id'";
    $perintah = "UPDATE User SET login_time = '$login_time', login_state = '$login_state' WHERE username = '$username' ";
    $eksekusi = mysqli_query($konek, $perintah);
    $cek      = mysqli_affected_rows($konek);

    if($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Data Berhasil Diupdate";
    }
    else{
        $response["kode"] = 0;
        $response["pesan"] = "Gagal Mengupdate Data";
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($konek);

<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::any('buscarListasUsuario/{id}', 'CancionController@buscarListasUsuario');

Route::any('buscarCancionesLista/{id}', 'CancionController@buscarCancionesLista');

Route::any('buscarUsuarioAgregaCancion/{id}', 'CancionController@buscarUsuarioAgregaCancion');

Route::any('buscarUsuarioCancion/{id}', 'CancionController@buscarUsuarioCancion');

Route::any('buscarHistorial/{id}', 'CancionController@buscarHistorial');

Route::any('buscarCancionGenero/{id}', 'CancionController@buscarCancionGenero');

Route::any('buscarCancion/{titulo}', 'CancionController@buscarCancion');

Route:any('buscarPortadaAlbum/{id}', 'CancionController@buscarPortada');

Route ::any('buscarAlbum/{id}', 'CancionController@buscarAlbum');

Route::any('recuperarAlbumes/{idUsuario}', 'CancionController@recuperarAlbumes');

Route::any('recuperarAlbum/{titulo}', 'CancionController@recuperarAlbum');

Route::any('cancionesPorAlbum/{idAlbum}', 'CancionController@cancionesPorAlbum');


Route::any('cancion/{id}', function($id){
    $filename = \App\Cancion::SearchById($id);

    //$file = public_path() . '/musica/' . $filename;
    $file = '/home/maritello/Escritorio/music/' . $filename;

       $mime_type = "audio/mp3";
    $headers = array(
        'Accept-Ranges: 0-' . (filesize($file) -1) ,

        'Content-Length:'.filesize($file),
        'Content-Type:' . $mime_type,
        'Content-Disposition: inline; filename="'.$filename.'"'

    );
    $fileContents = File::get($file);
    return Response::make($fileContents, 200, $headers);
});
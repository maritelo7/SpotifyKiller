<?php

namespace App\Http\Controllers;

use App\Album;
use App\Cancion;
use App\Historial;
use App\ListaReproduccion;
use App\UsuarioAgregaCancion;

use \Illuminate\Http;
use Illuminate\Http\Request;

class CancionController extends Controller
{
    //
    public function buscarCancion($titulo){
        $cancion = Cancion::Search($titulo)->orderBy('id', 'DESC')->paginate(100);

        $cancion->each(function ($cancion) {
            $cancion->genero;
            $cancion->usuario;
            $cancion->album;
        });

        $cancion = $cancion -> getCollection();

        return $cancion;
    }



    public function buscarCancionesLista($idListaReproduccion){
        $listaRep = UsuarioAgregaCancion::searchPorUsuario($idListaReproduccion)->orderBy('id', 'DESC')->paginate(100);

       $listaRep->each(function ($listaRep){
           $listaRep -> cancion -> usuario;
          $listaRep -> cancion -> album;
        });

        $listaRep = $listaRep->getCollection();
        return $listaRep;
    }



    public function buscarListasUsuario($idUsuario){
        $listaRep = ListaReproduccion::Search($idUsuario)->orderBy('id', 'DESC')->paginate(100);

        $listaRep = $listaRep -> getCollection();

        return $listaRep;
    }



    public function buscarUsuarioAgregaCancion($id){
        $listaRep = UsuarioAgregaCancion::Search($id)->orderBy('id', 'DESC')->paginate(100);

        $listaRep->each(function ($listaRep){
            $listaRep->usuario;
        });

        return $listaRep = $listaRep->getCollection();
    }

    public function buscarUsuarioCancion($id){
        $listaRep = Cancion::searchPorUsuario($id)->orderBy('id', 'DESC')->paginate(100);

        $listaRep->each(function ($listaRep){
            $listaRep->album;
            $listaRep->usuario;
            $listaRep->genero;
        });

        return $listaRep = $listaRep->getCollection();
    }



    public function buscarHistorial($id){
        $listaRep = Historial::Search($id)->orderBy('id', 'DESC')->paginate(100);

        $listaRep->each(function ($listaRep){
            $listaRep->cancion;
        });

        return $listaRep = $listaRep->getCollection();
    }


    public function buscarCancionGenero($id){
        $listaRep = Cancion::SearchGenero($id)->orderBy('id', 'DESC')->paginate(100);

        $listaRep->each(function ($listaRep){
            $listaRep->usuario;
            $listaRep->album;
        });

        return $listaRep = $listaRep->getCollection();
    }


    public function recuperarAlbumes($idUsuario){
        $album = Album::searchPorId($idUsuario)->orderBy('titulo', 'DESC') ->paginate(100);
        $album = $album -> getCollection();
        return $album;
    }

    public function recuperarAlbum($titulo){
        $album = Album::searchPorTitulo($titulo)->orderBy('titulo', 'DESC') ->paginate(100);

        $album->each(function ($album){
            $album->usuario;
        });

        $album = $album -> getCollection();
        return $album;
    }


    public function cancionesPorAlbum($idAlbum){
        $canciones = Cancion::searchPorAlbum($idAlbum)->orderBy('titulo', 'DESC') ->paginate(100);
        $canciones = $canciones -> getCollection();
        return $canciones;
    }

    public function buscarAlbum($id){
            $album = Album::Search($id);
            $arreglo = file_get_contents('/home/maritello/Escritorio/images/' . $album);
            $base = base64_encode($arreglo);
            return $base;
        return $base;
    }


    public function buscarCancionId($id){
        $listaRep = Cancion::SearchById($id);
        return $listaRep;
    }


}

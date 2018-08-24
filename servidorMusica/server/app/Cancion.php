<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Cancion extends Model
{
    //
    protected $table="cancion";
    protected $fillable=['titulo', 'formato', 'idGenero', 'path', 'colaboradores','idUsuarioSubioCancion', 'idAlbum', 'calidad'];
    public $timestamps = false;

    public function usuario(){
        return $this->belongsTo('App\Usuario', 'idUsuarioSubioCancion');
    }
    public function genero(){
        return $this->belongsTo('App\Genero', 'idGenero');
    }
    public function album(){
        return $this->belongsTo('App\Album', 'idAlbum');
    }

    public function scopeSearch($query, $titulo){
        return $query->where('titulo', 'LIKE', "%$titulo%");

    }
    public function scopeSearchGenero($query, $genero){
        return $query->where('idGenero', 'LIKE', "%$genero%");

    }
    public function scopeSearchById($query, $id){
        return $query->where('id', 'LIKE', "%$id%")->value('path');

    }

    public static function searchPorUsuario($id){
        return self::query()->where('idUsuarioSubioCancion', 'LIKE', "%$id%");
    }

    public static function searchPorAlbum($id){
        return self::query()->where('idAlbum', 'LIKE', "%$id%");
    }


}

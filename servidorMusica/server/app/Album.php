<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Album extends Model
{
    //
    protected $table="album";
    protected $fillable=['titulo', 'pathPortada', 'anioLanzamiento', 'companiaDiscografica', 'idUsuario'];
    public function cancion(){
        return $this->hasMany('App\Cancion');
    }
    public function usuario(){
        return $this->belongsTo('App\Usuario', 'idUsuario');
    }
    public function scopeSearch($query, $id){
        return $query->where('id', 'LIKE', "%$id%")->value('pathPortada');
    }

    public static function searchPorId($idUsuario){
        return self::query()->where('idUsuario', 'LIKE', "%$idUsuario%");
    }

    public static function searchPorTitulo($titulo){
        return self::query()->where('titulo', 'LIKE', "%$titulo%");
    }



}

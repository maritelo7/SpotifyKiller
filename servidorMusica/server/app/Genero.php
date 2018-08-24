<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Genero extends Model
{
    //
    protected $table="genero";
    protected $fillable=['genero'];
    public $timestamps = false;
    public function cancion(){
        return $this->hasMany('App\Cancion');
    }
}

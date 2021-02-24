package com.company.base;

public class EntradaInforme {
    private Manga manga;
    private Revista revista;

    public EntradaInforme(Manga manga, Revista revista) {
        this.manga = manga;
        this.revista = revista;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }
}

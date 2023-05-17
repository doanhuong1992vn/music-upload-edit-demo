package com.music_upload.repository.impl;

import com.music_upload.model.Music;
import com.music_upload.repository.MusicRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class MusicRepositoryImpl implements MusicRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Music> getAll() {
        TypedQuery<Music> query = entityManager.createQuery("from Music", Music.class);
        return query.getResultList();
    }

    @Override
    public void save(Music music) {
        if (music.getId() != null) {
            entityManager.merge(music);
        } else {
            entityManager.persist(music);
        }
    }

    @Override
    public Music getById(long id) {
        TypedQuery<Music> query = entityManager.createQuery("from Music where id = :id", Music.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(long id) {
        entityManager.createQuery("delete from Music where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}

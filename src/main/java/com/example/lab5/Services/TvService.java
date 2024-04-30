package com.example.lab5.Services;

import com.example.lab5.Repositories.Entities.TvEntity;
import com.example.lab5.Repositories.TvRepository;
import com.example.lab5.Util.AppError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TvService {
    private final TvRepository tvRepository;

    public Iterable<TvEntity> getAllTv(){

        return tvRepository.findAll();
    }

    public TvEntity loadUserById(int id){
        return tvRepository.findById(id).get();
    }

    public TvEntity saveTv(TvEntity tvEntity){
        return tvRepository.save(tvEntity);
    }

    public List<TvEntity> searchByLowPrice(double price){
        Iterable<TvEntity> tvEntities = getAllTv();
        List<TvEntity> list = new ArrayList<>();
        tvEntities.forEach(tvEntity -> {if (tvEntity.getPrice() < price) {list.add(tvEntity);}});
        return list;
    }

    public boolean existsTv(int id){
        return tvRepository.existsById(id);
    }


    public ResponseEntity<?> addTv(TvEntity tvEntity){
        try {
            tvRepository.save(tvEntity);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "No such user!"), HttpStatus.BAD_REQUEST);
        }
       return ResponseEntity.ok(HttpStatus.OK);
    }



    public ResponseEntity<?> deleteTv(int id){
        try{
            tvRepository.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "No such user!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }


}

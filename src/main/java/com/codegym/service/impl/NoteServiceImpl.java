package com.codegym.service.impl;

import com.codegym.model.Note;
import com.codegym.repository.NoteRepository;
import com.codegym.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class NoteServiceImpl implements NoteService {
    @PersistenceContext
    public EntityManager em;
    @Autowired
    private NoteRepository noteRepository;
    @Override
    public void save(Note note) {
        noteRepository.save(note);
    }

    @Override
    public void delete(Integer id) {
        noteRepository.delete(id);
    }

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public List<Note> serchNote(String keyword) {
        TypedQuery<Note> query = em.createQuery("select  c from Note c where c.title like CONCAT('%',:keyword,'%') ", Note.class);
        query.setParameter("keyword",keyword);
        return query.getResultList();
    }



    @Override
    public Note findById(Integer id) {
        return noteRepository.findOne(id);
    }



//    @Override
//    public void writeJSON() {
//        JSONArray noteListJSON = new JSONArray();
//        List<Note> notes = (List<Note>) noteRepository.findAll();
//        for (int i = 0; i < notes.size(); i++) {
//
//            JSONObject noteDetail = new JSONObject();
//
//            JSONObject noteObject = new JSONObject();
//            Note note = notes.get(i);
//            noteDetail.put("id", note.getTypeId());
//            noteDetail.put("title", note.getTitle());
//            noteDetail.put("content", note.getContent());
//            noteDetail.put("noteTypeId", note.getTypeNote().getId());
//
//            noteObject.put("note", noteDetail);
//
//            noteListJSON.add(noteObject);
//        }
//        //Write JSON file
//        try (FileWriter file = new FileWriter("D:/Program Files/ Export.json")) {
//
//            file.write(noteListJSON.toJSONString());
//            file.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//    @Override
  //  public void importJSON() {
//        //JSON parser object to parse read file
//        JSONParser jsonParser = new JSONParser();
//
//        try (FileReader reader = new FileReader("C:/demo/Note Import.json"))
//        {
//            //Read JSON file
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray noteListJSON = (JSONArray) obj;
//            System.out.println(noteListJSON);
//
//            //Iterate over employee array
//            for (int i=0; i<noteListJSON.size(); i++) {
//                JSONObject noteObject = (JSONObject) noteListJSON.get(i);
//                Note note = parseNoteObject(noteObject);
//                noteRepository.save(note);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
   // }

    //public Note parseNoteObject(JSONObject note) {
//        JSONObject noteObject = (JSONObject) note.get("note");
//
//        String title = (String) noteObject.get("title");
//
//        String content = (String) noteObject.get("content");
//
//        Long noteTypeId = (Long) noteObject.get("noteTypeId");
//        return new Note(title,content, TypeNoteRepository.findOne(noteTypeId));
 //   }


}

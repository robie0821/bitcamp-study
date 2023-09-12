package bitcamp.myapp.dao;

import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDao {
  int insert(Board board);

  List<Board> findAll(int category);

  Board findBy(int boardNo);

  int update(Board board);

  int updateCount(int boardNo);

  int delete(int boardNo);

  int insertFiles(Board board);

  AttachedFile findFileBy(int fileNo);

  int deleteFile(int fileNo);

  int deleteFiles(int boardNo);
}
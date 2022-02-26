package com.crud.tasks.trello.validator;


import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TrelloValidatorTest {
    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "test description", "test pos", "testId");

        //When & Then
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("test id", "test name", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

        //When
        List<TrelloBoard> validateTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertThat(validateTrelloBoards).isNotNull();
        assertEquals(1, validateTrelloBoards.size());
    }

    @Test
    public void testReturnEmptyBoardList() {
        //Given
        TrelloBoard testTrelloBoard = new TrelloBoard("test id", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(testTrelloBoard);
        //When
        List<TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertThat(validatedTrelloBoards).isNotNull();
        assertEquals(0, validatedTrelloBoards.size());
    }

}

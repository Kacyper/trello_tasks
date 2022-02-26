package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = List.of(new TrelloBoardDto("test id", "test name", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> fetchedTrelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoardDtos.size());
    }

    @Test
    void testCreateTrelloCard(){
        //given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test name", "test description", "test pos", "test id");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("test id", "test name","test@url.pl");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@test.pl");
        //when
        CreatedTrelloCardDto created = trelloService.createTrelloCard(trelloCardDto);
        //then
        assertThat(createdTrelloCardDto).isNotNull();
        assertEquals("test id", createdTrelloCardDto.getId());
        assertEquals("test name", createdTrelloCardDto.getName());
        assertEquals("test@url.pl", createdTrelloCardDto.getShortUrl());
    }



}

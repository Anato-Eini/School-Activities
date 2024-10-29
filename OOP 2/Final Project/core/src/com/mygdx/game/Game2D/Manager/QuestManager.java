package com.mygdx.game.Game2D.Manager;

import com.mygdx.game.Game2D.Quest.Quest;

import java.util.Comparator;
import java.util.PriorityQueue;

//Managing quests implemented with priority queue;
public class QuestManager {
    PriorityQueue<Quest> quests;

    public QuestManager() {
        quests = new PriorityQueue<>(Comparator.comparingInt(o -> o.priority));
    }

    public QuestManager addQuest(Quest quest) {
        quests.add(quest);
        return this;
    }

    public QuestManager removeQuest(Quest quest) {
        quests.remove(quest);
        return this;
    }
}

package tn.esprit.spring.event.demo.Service;

import tn.esprit.spring.event.demo.Model.FeedBack;

import java.util.List;

public interface FeedBackService {

    FeedBack addFeedback(Long eventId, Long userId, FeedBack feedback);

    FeedBack updateFeedback(Long id, FeedBack feedback);

    void deleteFeedback(Long id);

    FeedBack getFeedback(Long id);

    List<FeedBack> getAllFeedback();

    List<FeedBack> getFeedbackByEvent(Long eventId);

    List<FeedBack> getFeedbackByUser(Long userId);
}

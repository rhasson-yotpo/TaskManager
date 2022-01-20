package com.academy.finaltask.core.entities;


import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.util.List;


public enum Status {
    InProgress,
    Finished,
    NotStarted;
}

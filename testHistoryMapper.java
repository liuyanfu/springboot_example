package com.huawei.fuxi.appserv.mapper;

import com.huawei.fuxi.appserv.entity.history.HistoryEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static com.huawei.fuxi.appserv.utils.DateUtils.converStringToDate;
import static com.huawei.fuxi.appserv.utils.DateUtils.converStringToDateHHMMSS;

/**
 * Created by z00310580 on 2018/1/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class testHistoryMapper {
    Date now = new Date();
    @Autowired
    private HistoryMapper historyMapper;

    @Test
    @Rollback
    public void testInsertHistory() throws Exception {
        historyMapper.insert(
                "z00310580",
                converStringToDateHHMMSS("2018-01-20 10:11:52"),
                "1", "2", "action:1",
                "action:2", now, now, 0
        );
        List<HistoryEntity> lst = historyMapper.findByOperatorAndTypeAndTime(
                "1", "z00310580", converStringToDateHHMMSS("2018-01-20 10:11:52"));
        for (HistoryEntity history : lst) {
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
            Assert.assertEquals("action is same", history.getAction(), "2");
            Assert.assertEquals("time is same", history.getTime(), converStringToDateHHMMSS("2018-01-20 10:11:52"));
            Assert.assertEquals(history.getType(), "1");
            Assert.assertEquals(history.getBeforeActionContent(), "action:1");
            Assert.assertSame(history.getIsDeleted(), 0);
            Assert.assertEquals(history.getAfterActionContent(), "action:2");
        }

    }

    @Test
    @Rollback
    public void testFindByAll() throws Exception {

        List<HistoryEntity> lst = historyMapper.getAll();
        Assert.assertNotNull(lst);
    }

    @Test
    @Rollback
    public void testFindByFromDateToDate() throws Exception {
        List<HistoryEntity> lst = historyMapper.findAllByFromDateToDate(
                converStringToDate("2018-01-20"),
                converStringToDate("2018-01-21")
        );
        for (HistoryEntity history : lst) {
            Assert.assertEquals("time is same", history.getTime(), converStringToDateHHMMSS("2018-01-20 10:11:52"));
        }

    }

    @Test
    @Rollback
    public void testFindByOperator() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByOperator("z00310580");
        for (HistoryEntity history : lst) {
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
        }
    }

    @Test
    @Rollback
    public void testFindByOperatorAndFromDateToDate() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByOperatorAndFromDateToDate(
                "z00310580",
                converStringToDate("2018-01-20"),
                converStringToDate("2018-01-21")
        );
        for (HistoryEntity history : lst) {
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
        }
    }

    @Test
    @Rollback
    public void testFindByAction() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByAction("2");
        for (HistoryEntity history : lst) {
            Assert.assertEquals("action is same", history.getAction(), "2");
        }
    }

    @Test
    @Rollback
    public void testFindByTypeAndOperator() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByType("1");
        for (HistoryEntity history : lst) {
            Assert.assertEquals("type is same", history.getType(), "1");
        }
    }

    @Test
    @Rollback
    public void testFindByTypeAndOperatorAndFromDateToDate() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByTypeAndOperatorAndFromDateToDate(
                "1", "z00310580",
                converStringToDate("2018-01-20"),
                converStringToDate("2018-01-21")
        );
        for (HistoryEntity history : lst) {
            Assert.assertEquals("type is same", history.getType(), "1");
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
        }
    }

    @Test
    @Rollback
    public void testFindByActionAndOperator() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByActionAndOperator("2", "z00310580");
        for (HistoryEntity history : lst) {
            Assert.assertEquals("action is same", history.getAction(), "2");
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
        }
    }

    @Test
    @Rollback
    public void testFindByActionAndOperatorAndFromDateToDate() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByActionAndOperatorAndFromDateToDate(
                "2", "z00310580",
                converStringToDate("2018-01-20"),
                converStringToDate("2018-01-21")
        );
        for (HistoryEntity history : lst) {
            Assert.assertEquals("action is same", history.getAction(), "2");
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
        }
    }

    @Test
    @Rollback
    public void testFindByTypeAndAction() throws Exception {
        List<HistoryEntity> lst = historyMapper.findByTypeAndAction("2", "1");
        for (HistoryEntity history : lst) {
            Assert.assertEquals("action is same", history.getAction(), "2");
            Assert.assertEquals("type is same", history.getType(), "1");
        }
    }

    @Test
    @Rollback
    public void testFindByOperatorAndTypeAndAction() {
        List<HistoryEntity> lst = historyMapper.findByOperatorAndTypeAndAction(
                "2", "z00310580", "1");
        for (HistoryEntity history : lst) {
            Assert.assertEquals("action is same", history.getAction(), "2");
            Assert.assertEquals("type is same", history.getType(), "1");
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
        }
    }

    @Test
    @Rollback
    public void testFindByOperatorAndTypeAndActionAndFromDateToDate() {
        List<HistoryEntity> lst = historyMapper.findByOperatorAndTypeAndActionAndFromDateToDate(
                "2", "z00310580", "1",
                converStringToDate("2018-01-20"),
                converStringToDate("2018-01-21")
        );
        for (HistoryEntity history : lst) {
            Assert.assertSame("action is same", history.getAction(), 2);
            Assert.assertSame("type is same", history.getType(), "1");
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
        }
    }

    @Test
    @Rollback
    public void testFindByOperatorAndTypeAndTime() {
        List<HistoryEntity> lst = historyMapper.findByOperatorAndTypeAndTime(
                "1", "z00310580",
                converStringToDateHHMMSS("2018-01-20 10:11:52")
        );
        for (HistoryEntity history : lst) {
            Assert.assertEquals("type is same", history.getType(), "1");
            Assert.assertEquals("operator is same", history.getOperator(), "z00310580");
            Assert.assertEquals("time is same", history.getTime(), converStringToDateHHMMSS("2018-01-20 10:11:52"));
        }
    }

    @Test
    @Rollback
    public void testDeleteById() {
        historyMapper.insert(
                "z00310580",
                converStringToDateHHMMSS("2018-01-20 10:11:52"),
                "1", "2", "action:1",
                "action:2", now, now, 0
        );
        List<HistoryEntity> lst = historyMapper.findByOperatorAndTypeAndTime(
                "1", "z00310580", converStringToDateHHMMSS("2018-01-20 10:11:52"));
        for (HistoryEntity history : lst) {
            historyMapper.deleteById(history.getId());
        }
        List<HistoryEntity> lst2 = historyMapper.findByOperatorAndTypeAndTime(
                "1", "z00310580", converStringToDateHHMMSS("2018-01-20 10:11:52"));
        Assert.assertSame(lst2.size(), 0);
    }
}

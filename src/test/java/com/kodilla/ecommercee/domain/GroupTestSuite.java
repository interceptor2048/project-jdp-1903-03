package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.service.GroupService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTestSuite {
    @Autowired
    GroupService groupService;

    @Transactional
    @Test
    public void testGetGroups() {
        //Given
        Group group1 = new Group("Group1");
        groupService.saveGroup(group1);
        Group group2 = new Group("Group2");
        groupService.saveGroup(group2);
        Group group3 = new Group("Group3");
        groupService.saveGroup(group3);
        //When
        List<Group> listGroups = groupService.getGroupsList();
        //Then
        Assert.assertEquals(listGroups.size(), 3);
    }

    @Transactional
    @Test
    public void testGetGroup() {
        //Given
        Group group1 = new Group("Group1");
        groupService.saveGroup(group1);
        //When
        Long idGroup = groupService.findByGroupName("Group1").getGroupId();
        Group receivedGroup = groupService.getGroup(idGroup);
        String testGroupName = receivedGroup.getGroupName();
        //Then
        Assert.assertEquals(testGroupName, "Group1");
    }

    @Transactional
    @Test
    public void testCreateGroup() {
        //Given
        List<Group> emptyGroupList = groupService.getGroupsList();
        Group group2 = new Group("Group2");
        groupService.saveGroup(group2);
        //When
        List<Group> groupListWithCreatedGroup = groupService.getGroupsList();
        //Then
        Assert.assertEquals(emptyGroupList.size()+1, groupListWithCreatedGroup.size());
    }

    @Transactional
    @Test
    public void testUpdateGroup() {
        //Given
        Group group3 = new Group("Group3");
        groupService.saveGroup(group3);
        Long idTestedGroup = groupService.findByGroupName("Group3").getGroupId();
        //When
        Group group3Updated = new Group(idTestedGroup, "Group4", null);
        groupService.saveGroup(group3Updated);
        String groupNameAfterUpdate = groupService.getGroup(idTestedGroup).getGroupName();
        //Then
        Assert.assertEquals(groupNameAfterUpdate, "Group4");
        groupService.deleteById(idTestedGroup);
    }

    @Transactional
    @Test
    public void testCreateGroupWithProduct() {
        //Given
        Group group5 = new Group("Group5");
        Product product5 = new Product("Laptop", 2500.0);
        group5.getProductsList().add(product5);
        product5.setGroup(group5);
        groupService.saveGroup(group5);
        //When
        Long idGroup = groupService.findByGroupName("Group5").getGroupId();
        Product receivedProduct = groupService.getGroup(idGroup).getProductsList().get(0);
        //Then
        Assert.assertEquals(product5, receivedProduct);
    }
}
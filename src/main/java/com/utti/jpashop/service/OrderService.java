package com.utti.jpashop.service;

import com.utti.jpashop.domain.Delivery;
import com.utti.jpashop.domain.Member;
import com.utti.jpashop.domain.Order;
import com.utti.jpashop.domain.OrderItem;
import com.utti.jpashop.domain.item.Item;
import com.utti.jpashop.repository.ItemRepository;
import com.utti.jpashop.repository.MemberRepository;
import com.utti.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문하기
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문정보 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     * @param orderId
     */
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    /**
     * 주문 검색
     */
   /* public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
    */

}

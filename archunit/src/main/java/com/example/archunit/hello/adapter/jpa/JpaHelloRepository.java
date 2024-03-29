package com.example.archunit.hello.adapter.jpa;

import com.example.archunit.hello.business.model.Hello;
import com.example.archunit.hello.business.port.out.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaHelloRepository implements HelloRepository {

    @Autowired
    JpaHelloRepositoryDelegate delegate;

    @Override
    public void persist(final Hello hello) {
        delegate.save(hello);
    }

    @Override
    public List<Hello> findAll() {
        return delegate.findAll();
    }
}

interface JpaHelloRepositoryDelegate extends JpaRepository<Hello, UUID> {

}

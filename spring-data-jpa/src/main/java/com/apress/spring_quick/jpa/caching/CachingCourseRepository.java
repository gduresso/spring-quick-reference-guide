/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.apress.spring_quick.jpa.caching;

import com.apress.spring_quick.jpa.simple.Course;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

/**
 * User repository using Spring's caching abstraction.
 */
public interface CachingCourseRepository extends CrudRepository<Course, Long> {

    @Override
    @CacheEvict(value = "courseByName", key = "#p0.name")
    <S extends Course> S save(S entity);

    @Cacheable("courseByName")
    Course findByName(String name);

    @Override
    @CacheEvict(value = "courseByName", key = "#p0.name")
    void delete(Course course);

    @Override
    @CacheEvict(value = "courseByName", allEntries = true)
    void deleteById(Long aLong);

    @Override
    @CacheEvict(value = "courseByName", allEntries = true)
    void deleteAll();

}

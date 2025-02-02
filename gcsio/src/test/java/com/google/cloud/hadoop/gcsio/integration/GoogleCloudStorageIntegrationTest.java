/*
 * Copyright 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.hadoop.gcsio.integration;

import com.google.cloud.hadoop.gcsio.GoogleCloudStorage;
import com.google.cloud.hadoop.gcsio.GoogleCloudStorageImpl;
import com.google.cloud.hadoop.gcsio.GoogleCloudStorageOptions;
import com.google.cloud.hadoop.gcsio.PerformanceCachingGoogleCloudStorage;
import com.google.cloud.hadoop.gcsio.PerformanceCachingGoogleCloudStorageOptions;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GoogleCloudStorageIntegrationTest extends GoogleCloudStorageTest {

  @Parameterized.Parameters
  public static Collection<Object[]> getConstructorArguments() throws IOException {
    return Arrays.asList(
        new Object[] {getGoogleCloudStorage()},
        new Object[] {getPerformanceCachingGoogleCloudStorage()});
  }

  private static GoogleCloudStorage getPerformanceCachingGoogleCloudStorage() throws IOException {
    return new PerformanceCachingGoogleCloudStorage(
        getGoogleCloudStorage(GoogleCloudStorageTestHelper.getStandardOptionBuilder()),
        PerformanceCachingGoogleCloudStorageOptions.DEFAULT);
  }

  private static GoogleCloudStorage getGoogleCloudStorage() throws IOException {
    return getGoogleCloudStorage(GoogleCloudStorageTestHelper.getStandardOptionBuilder());
  }

  private static GoogleCloudStorage getGoogleCloudStorage(
      GoogleCloudStorageOptions.Builder optionsBuilder) throws IOException {
    return GoogleCloudStorageImpl.builder()
        .setOptions(optionsBuilder.build())
        .setCredentials(GoogleCloudStorageTestHelper.getCredentials())
        .build();
  }

  public GoogleCloudStorageIntegrationTest(GoogleCloudStorage gcs) {
    super(gcs);
  }
}
